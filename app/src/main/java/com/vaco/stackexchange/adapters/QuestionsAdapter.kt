package com.vaco.stackexchange.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.vaco.stackexchange.R
import com.vaco.stackexchange.mvvm.model.Questions
import kotlinx.android.synthetic.main.item_question_recycler_view.view.*
import java.util.*

class QuestionsAdapter(private var context: Context): RecyclerView.Adapter<QuestionsAdapter.ViewHolder>() {

    private var questions =  mutableListOf<Questions>()

    fun setQuestions(questions: MutableList<Questions>) {
        this.questions = questions
    }

    interface OnItemClickListener {
        fun onItemClick(position: Int)
    }

    private lateinit var onItemClickListener: OnItemClickListener

    fun setOnClickListener(listener: OnItemClickListener) {
        onItemClickListener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QuestionsAdapter.ViewHolder {
        val layoutInflater = LayoutInflater.from(context).inflate(R.layout.item_question_recycler_view, parent, false)
        return ViewHolder(layoutInflater, onItemClickListener)
    }

    override fun onBindViewHolder(holder: QuestionsAdapter.ViewHolder, position: Int) {
        holder.render(questions[position])
    }

    override fun getItemCount(): Int {
        return questions.size
    }

    inner class ViewHolder(itemView: View, listener: OnItemClickListener): RecyclerView.ViewHolder(itemView) {

        fun render (question: Questions) {
            Picasso.get().load(question.owner.profile_image).into(itemView.imageView)
            itemView.ownerTextView.text = question.owner.display_name
            itemView.titleTextView.text = question.title
            ("Tags: " + question.tags.joinToString (separator = ", ")).also { itemView.tagsTextView.text = it }
            itemView.dateTextView.text = Date(question.creation_date * 1000).toString()
            itemView.linkTextView.text = question.link
        }

        init {
            itemView.linkTextView.setOnClickListener{
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    listener.onItemClick(position)
                }
            }
        }

    }

}