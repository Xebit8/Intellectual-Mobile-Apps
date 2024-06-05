package ru.mirea.firsovav.timeservice

import java.io.BufferedReader
import java.io.InputStreamReader
import java.io.PrintWriter
import java.net.Socket

class SocketUtils {
    fun getReader(s: Socket): BufferedReader {
        return (BufferedReader(InputStreamReader(s.getInputStream())))
    }
    fun getWriter(s: Socket): PrintWriter {
        return PrintWriter(s.getOutputStream(), true)
    }
}