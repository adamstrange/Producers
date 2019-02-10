package com.strangea.producers

import android.content.Intent
import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import android.view.MenuItem
import com.squareup.picasso.Picasso
import com.strangea.producers.entities.ProducerResponse
import kotlinx.android.synthetic.main.activity_item_detail.*

/**
 * An activity representing a single Item detail screen. This
 * activity is only used on narrow width devices. On tablet-size devices,
 * item details are presented side-by-side with a list of items
 * in a [ItemListActivity].
 */
class ItemDetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_item_detail)
        setSupportActionBar(detailToolbar)

        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own detail action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val producerResponse = intent.getParcelableExtra<ProducerResponse>(ARG_ITEM);
        producerResponse.images?.let{
            Picasso.get().load(it[0].path).into(producerImageView)
        }
        if(!producerResponse.location.isNullOrEmpty()) {
            itemDetailTextView.text = producerResponse.location
            itemDetailTextView.append("\n\n")
        }
        itemDetailTextView.append(producerResponse.description)

        supportActionBar?.title = producerResponse.name
    }

    override fun onOptionsItemSelected(item: MenuItem) =
        when (item.itemId) {
            android.R.id.home -> {
                navigateUpTo(Intent(this, ItemListActivity::class.java))
                true
            }
            else -> super.onOptionsItemSelected(item)
        }

    companion object {
        const val ARG_ITEM = "item"
    }
}
