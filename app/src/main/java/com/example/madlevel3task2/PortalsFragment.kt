package com.example.madlevel3task2

import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.browser.customtabs.CustomTabsIntent
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResultListener
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.fragment_portals.*


/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class PortalsFragment : Fragment()  {

    private val portals = arrayListOf<Portal>()
    private val portalAdapter = PortalsAdapter(portals , onClickListener = this::openBrowser)

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_portals, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initViews()
        observeAddPortalResult()

    }

    fun openBrowser(view : View , portal : Portal){
        println(portal)
        val builder = CustomTabsIntent.Builder()
        val customTabsIntent = builder.build()
        customTabsIntent.launchUrl(context, Uri.parse(portal.url))
    }

    fun initViews(){
        rvPortals.layoutManager = LinearLayoutManager(context , RecyclerView.VERTICAL , false)
        rvPortals.adapter = portalAdapter
        rvPortals.layoutManager = GridLayoutManager(context, 2)

        }

    private fun observeAddPortalResult() {
        setFragmentResultListener(REQ_PORTAL_KEY) { key, bundle ->

            bundle.getParcelable<Portal>(BUNDLE_PORTAL_KEY)?.let {
                val portal = it
                portals.add(portal)
                portalAdapter.notifyDataSetChanged()
            }
        }
    }
}