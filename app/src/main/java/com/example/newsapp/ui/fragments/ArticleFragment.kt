package com.example.newsapp.ui.fragments

import android.app.Application
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebViewClient
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.newsapp.databinding.FragmentArticleBinding
import com.example.newsapp.db.ArticleDatabase
import com.example.newsapp.models.Article
import com.example.newsapp.repository.NewsRepository
import com.example.newsapp.ui.NewsActivity
import com.example.newsapp.ui.NewsViewModel
import com.example.newsapp.ui.NewsViewModelProviderFactory
import com.example.newsapp.util.Constants.Companion.ARTICLE_KEY
import com.google.android.material.snackbar.Snackbar

class ArticleFragment : Fragment() {

    private lateinit var binding : FragmentArticleBinding
    private lateinit var viewModel: NewsViewModel
    private lateinit var article : Article


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentArticleBinding.inflate(layoutInflater)
        article = requireArguments().getSerializable(ARTICLE_KEY) as Article
        Log.d("HEELO", article.url.toString())


        val newsRepository = NewsRepository(ArticleDatabase(requireContext()))
        val vmProviderFactory = NewsViewModelProviderFactory(Application(),newsRepository)
        viewModel = ViewModelProvider(this, vmProviderFactory).get(NewsViewModel::class.java)


        binding.webView.apply {
            webViewClient = WebViewClient()
            Log.d("HEELO", article.url.toString())
            article.url?.let { loadUrl(it) }
        }

        binding.fab.setOnClickListener {
            viewModel.saveArticle(article)
            Snackbar.make(requireView(),"Article saved successfully",Snackbar.LENGTH_SHORT).show()

        }


        return binding.root
    }

}