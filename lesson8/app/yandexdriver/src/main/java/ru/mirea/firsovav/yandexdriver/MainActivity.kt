package ru.mirea.firsovav.yandexdriver


import android.location.Location
import android.Manifest
import android.app.AlertDialog
import android.content.pm.PackageManager
import android.location.LocationManager
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.yandex.mapkit.MapKitFactory
import com.yandex.mapkit.RequestPoint
import com.yandex.mapkit.RequestPointType
import com.yandex.mapkit.directions.DirectionsFactory
import com.yandex.mapkit.directions.driving.DrivingOptions
import com.yandex.mapkit.directions.driving.DrivingRoute
import com.yandex.mapkit.directions.driving.DrivingRouter
import com.yandex.mapkit.directions.driving.DrivingSession
import com.yandex.mapkit.directions.driving.VehicleOptions
import com.yandex.mapkit.geometry.Point
import com.yandex.mapkit.map.CameraPosition
import com.yandex.mapkit.map.MapObjectCollection
import com.yandex.mapkit.mapview.MapView
import com.yandex.maps.mobile.R
import com.yandex.runtime.Error
import com.yandex.runtime.image.ImageProvider
import com.yandex.runtime.network.NetworkError
import com.yandex.runtime.network.RemoteError
import ru.mirea.firsovav.yandexdriver.databinding.ActivityMainBinding


class MainActivity : ComponentActivity(), DrivingSession.DrivingRouteListener {
    private lateinit var binding: ActivityMainBinding
    private val MAPKIT_API_KEY = "87fcc95c-ea07-482e-a732-b3577a95c390"
    private val LOCATION_PERMISSION_REQUEST_CODE = 100
    private lateinit var ROUTE_START_LOCATION: Point
    private val ROUTE_END_LOCATION: Point = Point(55.79485065063717, 37.711311747352006)
    private lateinit var SCREEN_CENTER: Point
    private lateinit var mapView: MapView
    private lateinit var mapObjects: MapObjectCollection
    private lateinit var drivingRouter: DrivingRouter
    private lateinit var drivingSession: DrivingSession
    private val colors = intArrayOf(-0x10000, -0xff0100, 0x00FFBBBB, -0xffff01)
    private lateinit var mLocationManager: LocationManager
    private lateinit var location: Location

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        MapKitFactory.initialize(this)
        DirectionsFactory.initialize(this)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        mapView = binding.mapview
        mapView.map.isRotateGesturesEnabled = false
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
            != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                this, arrayOf<String>(Manifest.permission.ACCESS_FINE_LOCATION),
                LOCATION_PERMISSION_REQUEST_CODE
            )
        }
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION)
            != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                this, arrayOf<String>(Manifest.permission.ACCESS_COARSE_LOCATION),
                LOCATION_PERMISSION_REQUEST_CODE
            )
        }

        mLocationManager = getSystemService(LOCATION_SERVICE) as LocationManager
        if (ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            return
        }
        location = mLocationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER) as Location

        ROUTE_START_LOCATION = Point(location.latitude, location.longitude)
        SCREEN_CENTER = Point(
            (ROUTE_START_LOCATION.latitude + ROUTE_END_LOCATION.latitude) / 2,
            (ROUTE_START_LOCATION.longitude + ROUTE_END_LOCATION.longitude) / 2
        )
        mapView.map.move(
            CameraPosition(
                SCREEN_CENTER, 10f, 0f, 0f
            )
        )
        drivingRouter = DirectionsFactory.getInstance().createDrivingRouter()
        mapObjects = mapView.map.mapObjects.addCollection()
        val marker = mapView.map.mapObjects.addPlacemark(
            Point(ROUTE_END_LOCATION.latitude, ROUTE_END_LOCATION.longitude),
            ImageProvider.fromResource(this, R.drawable.arrow)
        )
        marker.addTapListener { mapObject, point ->
            val builder = AlertDialog.Builder(this)
            builder.setTitle("Информация о заведении")
            builder.setMessage("Название: Burger King>\nАдрес: Преображенская площадь, 6\n")
            builder.setPositiveButton(
                "OK"
            ) { dialogInterface, i -> dialogInterface.dismiss() }
            val dialog = builder.create()
            dialog.show()
            false
        }
        submitRequest()
    }

    override fun onStop() {
        mapView.onStop()
        MapKitFactory.getInstance().onStop()
        super.onStop()
    }

    override fun onStart() {
        super.onStart()
        MapKitFactory.getInstance().onStart()
        mapView.onStart()
    }

    override fun onDrivingRoutes(list: MutableList<DrivingRoute>) {
        var color: Int
        for (i in 0 until list.size) {
            color = colors[i]
            mapObjects.addPolyline(list[i].geometry).setStrokeColor(color)
        }
    }

    override fun onDrivingRoutesError(error: Error) {
        var errorMessage = "Unknown error"
        if (error is RemoteError) {
            errorMessage = "Remote error"
        } else if (error is NetworkError) {
            errorMessage = "Network error"
        }
        Toast.makeText(this, errorMessage, Toast.LENGTH_SHORT).show()
    }

    private fun submitRequest() {
        val drivingOptions = DrivingOptions()
        val vehicleOptions = VehicleOptions()
        drivingOptions.setRoutesCount(4)
        val requestPoints = arrayListOf<RequestPoint>()
        requestPoints.add(
            RequestPoint(
                ROUTE_START_LOCATION,
                RequestPointType.WAYPOINT,
                null
            )
        )
        requestPoints.add(
            RequestPoint(
                ROUTE_END_LOCATION,
                RequestPointType.WAYPOINT,
                null
            )
        )
        drivingSession = drivingRouter.requestRoutes(
            requestPoints,
            drivingOptions,
            vehicleOptions,
            this
        )
    }
}
