package com.otakmager.fakeposts.home

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.otakmager.core.data.Resource
import com.otakmager.core.ui.PostsAdapter
import com.otakmager.fakeposts.R
import com.otakmager.fakeposts.databinding.FragmentHomeBinding
import com.otakmager.fakeposts.detail.DetailPostsActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {
    private val homeViewModel: HomeViewModel by viewModels()

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (activity != null) {

            val postsAdapter = PostsAdapter()
            postsAdapter.onItemClick = { selectedData ->
                val intent = Intent(activity, DetailPostsActivity::class.java)
                intent.putExtra(DetailPostsActivity.EXTRA_DATA, selectedData)
                startActivity(intent)
            }

            homeViewModel.posts.observe(viewLifecycleOwner) { posts ->
                if (posts != null) {
                    when (posts) {
                        is Resource.Loading -> binding.progressBar.visibility = View.VISIBLE
                        is Resource.Success -> {
                            binding.progressBar.visibility = View.GONE
                            postsAdapter.setData(posts.data)
                        }

                        is Resource.Error -> {
                            binding.progressBar.visibility = View.GONE
                            binding.viewError.root.visibility = View.VISIBLE
                            binding.viewError.tvError.text =
                                posts.message ?: getString(R.string.something_wrong)
                        }
                    }
                }
            }

            with(binding.rvPosts) {
                layoutManager = LinearLayoutManager(context)
                setHasFixedSize(true)
                adapter = postsAdapter
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}