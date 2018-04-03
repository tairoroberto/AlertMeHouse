package br.com.alertmehouse.alertmehouse.view.home

import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.os.Build
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.GridLayoutManager
import android.transition.ChangeBounds
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import br.com.alertmehouse.alertmehouse.R
import br.com.alertmehouse.alertmehouse.model.AlarmDevice
import br.com.alertmehouse.alertmehouse.utils.extension.showProgress
import br.com.alertmehouse.alertmehouse.utils.extension.showSnackBarError
import br.com.alertmehouse.alertmehouse.viewmodel.home.HomeViewModel
import br.com.alertmehouse.alertmehouse.viewmodel.home.HomeViewModelFactory
import dagger.android.support.AndroidSupportInjection
import kotlinx.android.synthetic.main.fragment_home.*
import org.jetbrains.anko.alert
import org.jetbrains.anko.okButton
import org.jetbrains.anko.toast
import javax.inject.Inject

/**
 * A simple [Fragment] subclass.
 */
class HomeFragment : Fragment() {

    @Inject
    lateinit var homeViewModelFactory: HomeViewModelFactory

    private val viewModel by lazy {
        ViewModelProviders.of(this, homeViewModelFactory).get(HomeViewModel::class.java)
    }

    private val list: MutableList<AlarmDevice> = ArrayList()

    private lateinit var adapter: HomeRecyclerAdapter

    override fun onAttach(context: Context?) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        adapter = HomeRecyclerAdapter(list, this::onItemClick)
        setHasOptionsMenu(true)
        retainInstance = true

        observeLoadingStatus()
        observeErrorStatus()
        observeResponse()
        observeAlarmDeviceResponse()
        getAllDevices()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        setAnimation()
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setRecyclerViewListDevices()
    }

    private fun setRecyclerViewListDevices() {
        val layoutManager = GridLayoutManager(activity, 2)
        recyclerView.layoutManager = layoutManager
        recyclerView.setHasFixedSize(true)
        recyclerView.adapter = adapter
        swipeRefreshLayout.setOnRefreshListener({ getAllDevices() })
    }

    private fun getAllDevices() {
        viewModel.getAlarmDevices()
    }

    private fun observeLoadingStatus() {
        viewModel.getLoadingStatus().observe(this, android.arch.lifecycle.Observer { isLoading -> showProgress(isLoading) })
    }

    private fun observeErrorStatus() {
        viewModel.getErrorStatus().observe(this, android.arch.lifecycle.Observer { msg -> showSnackBarError(msg.toString()) })
    }

    private fun observeResponse() {
        viewModel.getResponse().observe(this, android.arch.lifecycle.Observer { response -> showDevicesList(response) })
    }

    private fun observeAlarmDeviceResponse() {
        viewModel.getAlarmDeviceReponse().observe(this, android.arch.lifecycle.Observer { response -> showDevicesChanged(response) })
    }

    private fun showDevicesChanged(alarmDevice: AlarmDevice?) {
        activity?.alert {
            title = "Atenção"
            message = "Dispositivo: ${alarmDevice?.name} \n" +
                      if (alarmDevice?.status == true) "Habilitado" else " Desabilitado"
            okButton {}
        }
    }

    private fun setAnimation() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            val changeBounds = ChangeBounds()
            changeBounds.duration = 2000
            activity?.window?.sharedElementExitTransition = changeBounds
        }
    }

    private fun showProgress(b: Boolean?) {
        activity?.showProgress(recyclerView, progress, b == true)
        swipeRefreshLayout?.isRefreshing = false
    }

    private fun showSnackBarError(str: String) {
        activity?.showSnackBarError(recyclerView, str)
        swipeRefreshLayout?.isRefreshing = false
    }

    private fun showDevicesList(alarmDevices: List<AlarmDevice>?) {

        withoutData?.visibility = GONE
        if (alarmDevices == null || alarmDevices.isEmpty()) {
            withoutData?.visibility = VISIBLE
        }

        adapter.update(alarmDevices)
        swipeRefreshLayout?.isRefreshing = false
        showProgress(false)
    }

    private fun onItemClick(alarmDevice: AlarmDevice) {
        viewModel.setAlarmDevice(alarmDevice)
    }
}
