package com.otakmager.favorite


import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.OnBackPressedCallback
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.otakmager.core.ui.PostsAdapter
import com.otakmager.fakeposts.detail.DetailPostsActivity
import com.otakmager.fakeposts.di.FavoriteModuleDependencies
import com.otakmager.favorite.databinding.ActivityFavoriteBinding
import dagger.hilt.android.EntryPointAccessors
import javax.inject.Inject

class FavoriteActivity : AppCompatActivity() {
    @Inject
    lateinit var factory: ViewModelFactory

    private val favoriteViewModel: FavoriteViewModel by viewModels {
        factory
    }

    private lateinit var binding: ActivityFavoriteBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        DaggerFavoriteComponent.builder()
            .context(this)
            .appDependencies(
                EntryPointAccessors.fromApplication(
                    applicationContext,
                    FavoriteModuleDependencies::class.java
                )
            )
            .build()
            .inject(this)
        super.onCreate(savedInstanceState)
        binding = ActivityFavoriteBinding.inflate(layoutInflater)
        setContentView(binding.root)
        onBack()

        val postsAdapter = PostsAdapter()
        postsAdapter.onItemClick = { selectedData ->
            val intent = Intent(this, DetailPostsActivity::class.java)
            intent.putExtra(DetailPostsActivity.EXTRA_DATA, selectedData)
            startActivity(intent)
        }

        favoriteViewModel.favoritePosts.observe(this) { dataPosts ->
            postsAdapter.setData(dataPosts)
            binding.viewEmpty2.visibility =
                if (dataPosts.isNotEmpty()) View.GONE else View.VISIBLE
        }

        with(binding.rvPosts) {
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
            adapter = postsAdapter
        }
    }

    private fun onBack() {
        onBackPressedDispatcher.addCallback(this, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                finish()
            }
        })
    }
}