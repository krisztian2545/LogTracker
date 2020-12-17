package hu.suliprojekt.logtracker.screens.applist

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import hu.suliprojekt.logtracker.R
import hu.suliprojekt.logtracker.database.AppListDatabase
import hu.suliprojekt.logtracker.databinding.FragmentAppListBinding

class AppListFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding = DataBindingUtil.inflate<FragmentAppListBinding>(inflater, R.layout.fragment_app_list, container, false)

        val application = requireNotNull(this.activity).application
        val database = AppListDatabase.getInstance(application).appListDatabaseDao

        val viewModelFactory = AppListViewModelFactory(database, application)
        val viewModel = ViewModelProvider(this, viewModelFactory).get(AppListViewModel::class.java)

        binding.lifecycleOwner = this
        binding.appListViewModel = viewModel

        val adapter = AppListItemAdapter(AppListItemListener { appName ->
            Toast.makeText(context, appName, Toast.LENGTH_SHORT).show()
            viewModel.onAppListItemClicked(appName)
        })

        viewModel.navigateToAppLogs.observe(viewLifecycleOwner, Observer { appName ->
            appName?.let {
                this.findNavController().navigate(AppListFragmentDirections.actionAppListFragmentToLogListFragment(appName))
                viewModel.onAppLogsNavigated()
            }
        })

        binding.appListRecyclerView.adapter = adapter
        binding.appListRecyclerView.addItemDecoration(DividerItemDecoration(this.context, DividerItemDecoration.VERTICAL))

        viewModel.appList.observe(viewLifecycleOwner, Observer {
            it?.let {
                adapter.submitList(it)
            }
        })

        viewModel.isServerAvailable.observe(viewLifecycleOwner, Observer {
            if (!it) {
                Toast.makeText(context, "Failed to connect to the server. Check if you have internet connection.", Toast.LENGTH_LONG).show()
                viewModel.userNotified()
            }
        })

        return binding.root
    }

}