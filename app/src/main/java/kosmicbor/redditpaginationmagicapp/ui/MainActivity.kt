package kosmicbor.redditpaginationmagicapp.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.coroutineScope
import by.kirich1409.viewbindingdelegate.viewBinding
import kosmicbor.redditpaginationmagicapp.R
import kosmicbor.redditpaginationmagicapp.databinding.ActivityMainBinding
import kosmicbor.redditpaginationmagicapp.di.MAIN_SCREEN_VIEWMODEL_NAME
import kotlinx.coroutines.flow.collectLatest
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.qualifier.named

class MainActivity : AppCompatActivity() {

    private val mainViewModel: MainScreenViewModel by viewModel(named(MAIN_SCREEN_VIEWMODEL_NAME))
    private val binding: ActivityMainBinding by viewBinding(ActivityMainBinding::bind)
    private val adapter by lazy(LazyThreadSafetyMode.NONE) {
        MainPagingDataAdapter(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        binding.mainscreenRecyclerview.adapter = adapter

        lifecycle.coroutineScope.launchWhenCreated {
            mainViewModel.getPostsList()
            mainViewModel.query.collectLatest(adapter::submitData)
        }


    }
}