package id.aasumitro.made.ui.search

import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.view.View
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.ImageView
import android.widget.NumberPicker
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import id.aasumitro.made.R
import id.aasumitro.made.base.BaseActivity
import id.aasumitro.made.data.entity.Movie
import id.aasumitro.made.data.entity.Show
import id.aasumitro.made.ui.detail.DetailActivity
import id.aasumitro.made.ui.detail.DetailActivity.Companion.ALL
import id.aasumitro.made.ui.detail.DetailActivity.Companion.DATA_ENTITY
import id.aasumitro.made.ui.detail.DetailActivity.Companion.EXTRA_DATA
import id.aasumitro.made.ui.detail.DetailActivity.Companion.MOVIE
import id.aasumitro.made.ui.detail.DetailActivity.Companion.SHOW
import id.aasumitro.made.ui.rv.adapter.MovieAdapter
import id.aasumitro.made.ui.rv.adapter.ShowAdapter
import id.aasumitro.made.ui.rv.listener.MovieListener
import id.aasumitro.made.ui.rv.listener.ShowListener
import kotlinx.android.synthetic.main.component_toolbar.*
import kotlinx.android.synthetic.main.content_search.*
import java.util.*


class SearchActivity : BaseActivity(R.layout.activity_search), MovieListener, ShowListener {

    private val mViewModel: SearchViewModel by lazy {
        ViewModelProviders
            .of(this)
            .get(SearchViewModel::class.java)
    }

    private var mType = MutableLiveData<String>()
    private var mYear = MutableLiveData<String>()

    init {
        mType.postValue(MOVIE)
        mYear.postValue(ALL)
    }

    override fun initView() {
        initToolbar(back = true)
        setToolbarSearch(true)

        mYear.observe(this, Observer { year_text.text = it })
        mType.observe(this, Observer { type_text.text = it })

        initList()
        inputWatcher()
        onLoadingState()
        onErrorState()
    }

    override fun initListener() {
        layout_year.setOnClickListener { showYearDialog() }
        layout_type.setOnClickListener { showTypeDialog() }
    }

    private fun inputWatcher() {
        toolbar_search.setOnEditorActionListener { _, actionId, _ ->
            var handled = false
            if (actionId == EditorInfo.IME_ACTION_SEARCH ||
                actionId == EditorInfo.IME_ACTION_UNSPECIFIED
            ) {
                toolbar_search.clearFocus()
                val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                imm.hideSoftInputFromWindow(toolbar_search.windowToken, 0)
                val mQueryPush = toolbar_search.text.toString()
                val mYearPush = if (year_text.text.toString() == ALL) null else year_text.text.toString()
                searchState(mQueryPush, mYearPush?.toInt())
                handled = true
            }
            handled
        }
    }

    private fun initList() {
        search_recycler_view.setHasFixedSize(true)
        val mLayoutManager: RecyclerView.LayoutManager =
            GridLayoutManager(this, 2)
        search_recycler_view.layoutManager = mLayoutManager
        search_recycler_view.itemAnimator = DefaultItemAnimator()
    }

    private fun searchState(query: String, year: Int? = null) {
        mType.observe(this, Observer {
            when (it) {
                MOVIE -> mViewModel.movies(query, year).observe(this, searchMovie())
                SHOW -> mViewModel.shows(query, year).observe(this, searchShows())
            }
        })
    }

    private fun searchMovie() = Observer<ArrayList<Movie>> {
        if (it.isEmpty()) {
            emptyLayout()
        } else {
            showRecyclerView()
            search_recycler_view.adapter = MovieAdapter(it, this)
        }
    }

    private fun searchShows() = Observer<ArrayList<Show>> {
        if (it.isEmpty()) {
            emptyLayout()
        } else {
            showRecyclerView()
            search_recycler_view.adapter = ShowAdapter(it, this)
        }
    }

    private fun onLoadingState() {
        mViewModel
            .mLoadingState
            .observe(this, Observer {
                onProgress(it)
            })
    }

    private fun onErrorState() {
        mViewModel
            .mErrorState
            .observe(this, Observer {
                errorLayout(it)
                if (it == true) {
                    mViewModel
                        .mErrorMessage
                        .observe(
                            this,
                            errorMessage()
                        )
                }
            })
    }

    private fun errorMessage() = Observer<String> {
        onError(it)
    }

    private fun errorLayout(state: Boolean) {
        if (state) {
            emptyLayout()
            //image_error.setImageResource(R.id.)
            //text_error.text = "Something went Wrong"
        }
    }

    private fun emptyLayout() {
        empty_search.visibility = View.VISIBLE
        search_recycler_view.visibility = View.GONE
    }

    private fun showRecyclerView() {
        empty_search.visibility = View.GONE
        search_recycler_view.visibility = View.VISIBLE
    }

    private fun showYearDialog() {
        val year = Calendar.getInstance().get(Calendar.YEAR)
        val mDialog = Dialog(this@SearchActivity)
        mDialog.setContentView(R.layout.component_picker_dialog)

        val set = mDialog.findViewById<View>(R.id.button_set) as Button
        val clear = mDialog.findViewById<View>(R.id.button_clear) as Button
        val cancel = mDialog.findViewById<View>(R.id.button_cancel) as ImageView
        val picker = mDialog.findViewById<View>(R.id.number_picker) as NumberPicker
        val mQueryPush = toolbar_search.text.toString()

        picker.maxValue = year + 50
        picker.minValue = year - 50
        picker.value = year
        picker.wrapSelectorWheel = false
        picker.descendantFocusability = NumberPicker.FOCUS_BLOCK_DESCENDANTS
        picker.setOnValueChangedListener { _, _, data -> mYear.postValue(data.toString()) }

        set.setOnClickListener {
            mYear.postValue(picker.value.toString())
            if (mQueryPush.isNotEmpty()) {
                searchState(mQueryPush, picker.value)
            }
            mDialog.dismiss()
        }
        clear.setOnClickListener {
            mYear.postValue(ALL)
            if (mQueryPush.isNotEmpty()) {
                searchState(mQueryPush)
            }
            mDialog.dismiss()
        }
        cancel.setOnClickListener {
            mDialog.dismiss()
        }

        mDialog.show()
    }

    private fun showTypeDialog() {
        val mDialog = Dialog(this@SearchActivity)
        mDialog.setContentView(R.layout.component_picker_dialog)

        val set = mDialog.findViewById<View>(R.id.button_set) as Button
        val clear = mDialog.findViewById<View>(R.id.button_clear) as Button
        val cancel = mDialog.findViewById<View>(R.id.button_cancel) as ImageView
        val picker = mDialog.findViewById<View>(R.id.number_picker) as NumberPicker

        picker.maxValue = 1
        picker.minValue = 0
        picker.displayedValues = arrayOf(getString(R.string.title_movie), getString(R.string.title_show))

        picker.setOnValueChangedListener { _, _, data ->
            when (data) {
                0 -> mType.postValue(MOVIE)
                1 -> mType.postValue(SHOW)
            }
            mDialog.dismiss()
        }

        clear.visibility = View.GONE
        set.setOnClickListener {
            when (picker.value) {
                0 -> mType.postValue(MOVIE)
                1 -> mType.postValue(SHOW)
            }
            mDialog.dismiss()
        }

        cancel.setOnClickListener {
            mDialog.dismiss()
        }

        mDialog.show()
    }

    override fun onMovieSelected(movie: Movie?) {
        val intent = Intent(this, DetailActivity::class.java)
        intent.putExtra(DATA_ENTITY, MOVIE)
        intent.putExtra(EXTRA_DATA, movie)
        startActivity(intent)
    }

    override fun onShowSelected(show: Show?) {
        val intent = Intent(this, DetailActivity::class.java)
        intent.putExtra(DATA_ENTITY, SHOW)
        intent.putExtra(EXTRA_DATA, show)
        startActivity(intent)
    }

}
