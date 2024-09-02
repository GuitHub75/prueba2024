package com.cocodev2500.androidcomponent.ui.fragments.map

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavDestination.Companion.createRoute
import com.cocodev2500.androidcomponent.R
import com.cocodev2500.androidcomponent.databinding.ActivityMainBinding
import com.cocodev2500.androidcomponent.databinding.FragmentMapBinding
import com.cocodev2500.androidcomponent.entities.routes.RouteResponse
import com.cocodev2500.androidcomponent.repositories.routeRepository
import com.cocodev2500.androidcomponent.viewModels.route.routeViewModel
import com.cocodev2500.androidcomponent.viewModels.route.routeViewModelFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Polyline
import com.google.android.gms.maps.model.PolylineOptions
import kotlin.concurrent.thread

class mapFragment : Fragment(), OnMapReadyCallback {

    private lateinit var binding: FragmentMapBinding
    private lateinit var map : GoogleMap
    private var api_key  : String = ""
    private var start : String =""
    private var end : String =""
    var poly : Polyline? =null

    private lateinit var viewModelRoute : routeViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = FragmentMapBinding.inflate(inflater, container, false)
        return binding.root



    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val mapFragment = childFragmentManager.findFragmentById(R.id.map) as? SupportMapFragment
         mapFragment?.getMapAsync(this)

        val repositoryViewModel = routeRepository()
        val viewModelFactory = routeViewModelFactory(repositoryViewModel)
        viewModelRoute =ViewModelProvider(this,viewModelFactory).get(routeViewModel::class.java)

        binding.btnCalcularRuta.setOnClickListener {
            start = ""
            end = ""
            poly?.remove()
            if (::map.isInitialized) {
                    map.setOnMapClickListener {
                        if (start.isEmpty()){
                            //Primero va la latitud y despues la longitud.
                            start = "${it.longitude},${it.latitude}"
                        }else if(end.isEmpty()){
                            end = "${it.longitude},${it.latitude}"
                            var api_key = "5b3ce3597851110001cf62487f937390404f4048b1619821e91580bc";
                            viewModelRoute.getRoute(api_key,start,end)
                        }
                    }
            } else {
                Toast.makeText(context, "Mapa no inicializado", Toast.LENGTH_SHORT).show()
            }
        }


        //OBSERVABLES
        viewModelRoute.mRouteResponse.observe(viewLifecycleOwner){
            if (it.code() == 200){
                val data =it.body()
                drawRoute(data!!)
            }else{
                Toast.makeText(context, "Ha ocurrido un error al intentar", Toast.LENGTH_SHORT).show()
            }
        }

    }


    private fun drawRoute(routeResponse : RouteResponse){
        val polylineOptions = PolylineOptions()
        routeResponse.features.first().geometry.coordinates.forEach{
            polylineOptions.add(LatLng(it[1],it[0]))
        }
        poly = map.addPolyline(polylineOptions)
    }

    override fun onMapReady(_map: GoogleMap) {
       map = _map
       map.mapType = GoogleMap.MAP_TYPE_TERRAIN
    }




}
