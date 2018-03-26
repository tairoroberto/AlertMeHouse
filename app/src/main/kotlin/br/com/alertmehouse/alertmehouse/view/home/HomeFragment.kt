package br.com.alertmehouse.alertmehouse.view.home

import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.os.Build
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.transition.ChangeBounds
import android.view.*
import android.view.View.GONE
import android.view.View.VISIBLE
import android.widget.ImageView
import br.com.alertmehouse.alertmehouse.R
import br.com.alertmehouse.alertmehouse.model.AlarmDevice
import br.com.alertmehouse.alertmehouse.utils.extension.showProgress
import br.com.alertmehouse.alertmehouse.utils.extension.showSnackBarError
import br.com.alertmehouse.alertmehouse.viewmodel.home.HomeViewModel
import br.com.alertmehouse.alertmehouse.viewmodel.home.HomeViewModelFactory
import com.google.firebase.analytics.FirebaseAnalytics
import com.remoteok.io.app.view.home.SearchAdapter
import dagger.android.support.AndroidSupportInjection
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.coroutines.experimental.Job
import org.jetbrains.anko.alert
import org.jetbrains.anko.toast
import org.jetbrains.anko.yesButton
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

    private val list: MutableList<Job> = ArrayList()

    private lateinit var adapter: HomeRecyclerAdapter

    private lateinit var adapterSearch: SearchAdapter

    private val suggestions: MutableList<String> = ArrayList()

    private val filteredValues = ArrayList<String>()

    private lateinit var tracker: FirebaseAnalytics

    override fun onAttach(context: Context?) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        adapter = HomeRecyclerAdapter(activity, list, this::onItemClick)
        setHasOptionsMenu(true)
        retainInstance = true

        observeLoadingStatus()
        //observeErrorStatus()
        observeResponse()
        getAllDevices()

        tracker = FirebaseAnalytics.getInstance(activity)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        setAnimation()
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setRecyclerViewListJobs()

    }

    private fun setRecyclerViewListJobs() {
        val layoutManager = LinearLayoutManager(activity)
        recyclerView.layoutManager = layoutManager
        recyclerView.setHasFixedSize(true)
        recyclerView.adapter = adapter
        swipeRefreshLayout.setOnRefreshListener({ getAllDevices() })
    }

    private fun getAllDevices() {
        viewModel.getDevices()
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

    private fun showDevicesList(jobs: List<AlarmDevice>?) {

        withoutData?.visibility = GONE
        if (jobs == null || jobs.isEmpty()) {
            withoutData?.visibility = VISIBLE
        }

        adapter.update(jobs)
        swipeRefreshLayout?.isRefreshing = false
        showProgress(false)
    }

    private fun onItemClick(job: Job, imageView: ImageView) {

        activity?.toast("Teste alert")
    }

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        inflater?.inflate(R.menu.main, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.about) {
            //activity?.browse("https://www.paypal.com/your_paypal")
            activity?.alert {
                title = "About"
                message = "About Message"
                yesButton { }
            }?.show()
        }
        return true
    }
}
