package com.formationandroid.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.TextView
import androidx.fragment.app.FragmentTransaction
import androidx.preference.PreferenceManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.formationandroid.fragments.MemosAdapter.MemoViewHolder
import com.formationandroid.fragments.activites.DetailActivity
import com.formationandroid.fragments.activites.MainActivity
import com.formationandroid.fragments.bdd.MemoDTO
import com.formationandroid.fragments.fragments.DetailFragment

class MemosAdapter(private var listeMemoDTO: MutableList<MemoDTO>, private val mainActivity: MainActivity) : RecyclerView.Adapter<MemoViewHolder>()
{

	override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MemoViewHolder
	{
		val viewMemo = LayoutInflater.from(parent.context).inflate(R.layout.item_memo, parent, false)
		return MemoViewHolder(viewMemo)
	}

	override fun onBindViewHolder(holder: MemoViewHolder, position: Int)
	{
		holder.textViewIntitule.text = listeMemoDTO[position].intitule
	}

	override fun getItemCount(): Int
	{
		return listeMemoDTO.size
	}

	/**
	 * Mise à jour de la liste.
	 * @param listeMemoDTO: MutableList<MemoDTO>
	 */
	fun updateMemos(listeMemoDTO: MutableList<MemoDTO>)
	{
		this.listeMemoDTO = listeMemoDTO
		notifyDataSetChanged()
	}

	/**
	 * ViewHolder.
	 */
	inner class MemoViewHolder(itemView: View) : ViewHolder(itemView)
	{

		// Vue intitulé mémo :
		var textViewIntitule: TextView = itemView.findViewById(R.id.memo_intitule)


        /**
		 * Constructeur.
		 */
		init
		{
            // listener :
			itemView.setOnClickListener {

				// enregistrement de la position du dernier item cliqué :
				val preferences = PreferenceManager.getDefaultSharedPreferences(itemView.context)
				val editor = preferences.edit()
				editor.putInt(MainActivity.CLE_DERNIERE_POSITION, adapterPosition)
				editor.apply()

				// affichage du détail, selon qu'on est en mode smartphone ou en mode tablette :
				if (mainActivity.findViewById<FrameLayout>(R.id.conteneur_fragment) != null)
				{
					// mode tablette :
					val fragment = DetailFragment()
					val bundle = Bundle()
					bundle.putString(DetailFragment.EXTRA_MEMO, listeMemoDTO[adapterPosition].intitule)
					fragment.arguments = bundle

					// transaction :
					val transaction: FragmentTransaction = mainActivity.supportFragmentManager.beginTransaction()
					transaction.replace(R.id.conteneur_fragment, fragment)
					transaction.commit()
				}
				else
				{
					// mode smartphone :
					val intent = Intent(itemView.context, DetailActivity::class.java)
					intent.putExtra(DetailActivity.EXTRA_MEMO, listeMemoDTO[adapterPosition].intitule)
					itemView.context.startActivity(intent)
				}
			}
		}
	}

}