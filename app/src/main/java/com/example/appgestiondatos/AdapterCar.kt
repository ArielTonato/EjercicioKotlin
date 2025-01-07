package com.example.appgestiondatos

import android.content.ClipData.Item
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.appgestiondatos.databinding.ItemCarBinding
import com.example.appgestiondatos.model.Car

class AdapterCar(var cars: List<Car> = emptyList()):RecyclerView.Adapter<AdapterCar.AdapterCardViewHolder>(){

    //Funciones para editar y eliminar
    lateinit var setOnClickListenerCardEdit: (Car) -> Unit
    lateinit var setOnClickListenerCardDelete: (Car) -> Unit

    inner class AdapterCardViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
        private var binding: ItemCarBinding = ItemCarBinding.bind(itemView)

        fun bind(car: Car){
            with(binding){
                txtMarca.text = car.marca
                txtModelo.text = car.modelo
                txtPreferencias.text = car.preferencias

                btnEditar.setOnClickListener{
                    setOnClickListenerCardEdit(car)
                }

                btnEliminar.setOnClickListener{
                    setOnClickListenerCardDelete(car)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdapterCardViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_car, parent, false)
        return AdapterCardViewHolder(view)
    }

    override fun getItemCount(): Int {
        return cars.size
    }

    override fun onBindViewHolder(holder: AdapterCardViewHolder, position: Int) {
        val car = cars[position]
        holder.bind(car)
    }

    fun updateListCars(newCars: List<Car>){
        this.cars = newCars
        notifyDataSetChanged()
    }
}