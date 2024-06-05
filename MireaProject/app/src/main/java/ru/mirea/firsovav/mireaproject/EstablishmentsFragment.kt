package ru.mirea.firsovav.mireaproject

import android.app.AlertDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.res.ResourcesCompat
import androidx.preference.PreferenceManager
import org.osmdroid.config.Configuration
import org.osmdroid.util.GeoPoint
import org.osmdroid.views.MapView
import org.osmdroid.views.overlay.Marker
import org.osmdroid.views.overlay.ScaleBarOverlay
import org.osmdroid.views.overlay.compass.CompassOverlay
import org.osmdroid.views.overlay.compass.InternalCompassOrientationProvider
import org.osmdroid.views.overlay.mylocation.GpsMyLocationProvider
import org.osmdroid.views.overlay.mylocation.MyLocationNewOverlay

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [EstablishmentsFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class EstablishmentsFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }

    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment EstablishmentsFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            EstablishmentsFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
    private lateinit var mapView: MapView
    private lateinit var locationNewOverlay: MyLocationNewOverlay

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Configuration.getInstance().load(
            activity,
            activity?.let { PreferenceManager.getDefaultSharedPreferences(it.applicationContext) }
        )
        val view = inflater.inflate(R.layout.fragment_establishments, container, false)
        mapView = view.findViewById(R.id.mapView) as MapView

        mapView.setZoomRounding(true)
        mapView.setMultiTouchControls(true)

        val mapController = mapView.controller
        mapController.setZoom(15.0)
        val startPoint = GeoPoint(55.794229, 37.700772)
        mapController.setCenter(startPoint)

        locationNewOverlay =
            MyLocationNewOverlay(GpsMyLocationProvider(view.context), mapView)
        locationNewOverlay.enableMyLocation()
        mapView.overlays.add(this.locationNewOverlay)

        val compassOverlay = CompassOverlay(
            view.context, InternalCompassOrientationProvider(
                view.context
            ), mapView
        )
        compassOverlay.enableCompass()
        mapView.overlays.add(compassOverlay)

        val context = view.context
        val dm = context.resources.displayMetrics;
        val scaleBarOverlay = ScaleBarOverlay(mapView);
        scaleBarOverlay.setCentred(true);
        scaleBarOverlay.setScaleBarOffset(dm.widthPixels / 2, 10);
        mapView.overlays.add(scaleBarOverlay)

        val markerMIREA = Marker(mapView)
        markerMIREA.position = GeoPoint(55.794229, 37.700772)
        markerMIREA.setOnMarkerClickListener { marker, mapView ->
            val builder = AlertDialog.Builder(view.context)
            builder.setTitle("Информация о заведении")
            builder.setMessage("Название: Институт кибербезопасности\nАдрес: Стромынка, 20\n")
            builder.setPositiveButton(
                "OK"
            ) { dialogInterface, i -> dialogInterface.dismiss() }
            val dialog = builder.create()
            dialog.show()
            true
        }
        mapView.overlays.add(markerMIREA)
        markerMIREA.icon = ResourcesCompat.getDrawable(
            resources, org.osmdroid.library.R.drawable.osm_ic_follow_me_on, null
        )

        markerMIREA.title = "ИКБ"

        val markerBK = Marker(mapView)
        markerBK.position = GeoPoint(55.79485065063717, 37.711311747352006)
        markerBK.setOnMarkerClickListener { marker, mapView ->
            val builder = AlertDialog.Builder(view.context)
            builder.setTitle("Информация о заведении")
            builder.setMessage("Название: Burger King\nАдрес: Преображенская площадь, 6\n")
            builder.setPositiveButton(
                "OK"
            ) { dialogInterface, i -> dialogInterface.dismiss() }
            val dialog = builder.create()
            dialog.show()
            true
        }
        mapView.overlays.add(markerBK)
        markerBK.icon = ResourcesCompat.getDrawable(
            resources, org.osmdroid.library.R.drawable.osm_ic_follow_me_on, null
        )

        markerBK.title = "Бургер Кинг"

        val markerPerekrestok = Marker(mapView)
        markerBK.position = GeoPoint(55.795739, 37.700467)
        markerBK.setOnMarkerClickListener { marker, mapView ->
            val builder = AlertDialog.Builder(view.context)
            builder.setTitle("Информация о заведении")
            builder.setMessage("Название: Перекресток\nАдрес: Улица Стромынка, 25 ст1\n")
            builder.setPositiveButton(
                "OK"
            ) { dialogInterface, i -> dialogInterface.dismiss() }
            val dialog = builder.create()
            dialog.show()
            true
        }
        mapView.overlays.add(markerBK)
        markerBK.icon = ResourcesCompat.getDrawable(
            resources, org.osmdroid.library.R.drawable.osm_ic_follow_me_on, null
        )

        markerBK.title = "магазин Перекресток"
        return view
    }

    override fun onResume() {
        super.onResume()
        Configuration.getInstance().load(
            activity,
            activity?.let { PreferenceManager.getDefaultSharedPreferences(it.applicationContext) }
        )
        mapView.onResume()
    }

    override fun onPause() {
        super.onPause()
        Configuration.getInstance().save(
            activity,
            activity?.let {
                PreferenceManager.getDefaultSharedPreferences(
                    it.applicationContext
                )
            }
        )
        mapView.onPause()
    }
}