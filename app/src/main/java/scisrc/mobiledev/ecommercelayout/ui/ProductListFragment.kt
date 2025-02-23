package scisrc.mobiledev.ecommercelayout.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import scisrc.mobiledev.ecommercelayout.CartManager
import scisrc.mobiledev.ecommercelayout.FavoritesManager
import scisrc.mobiledev.ecommercelayout.ProductModel
import scisrc.mobiledev.ecommercelayout.R

class ProductListFragment : Fragment() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: ProductAdapter
    private val productList = mutableListOf<ProductModel>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_product_list, container, false)

        recyclerView = view.findViewById(R.id.recycler_product_list)
        recyclerView.layoutManager = LinearLayoutManager(context)

        // ✅ Mock Data สำหรับแสดงผลสินค้า
        productList.apply {
            add(ProductModel("Puma LaMelo Ball MB.01", "Rick and Morty", 229.00, R.drawable.mb01))
            add(ProductModel("Puma LaMelo Ball MB.01", "Iridescent", 110.00, R.drawable.mb02))
            add(ProductModel("Puma LaMelo Ball MB.01", "UFO", 71.00, R.drawable.mb03))
        }

        // ✅ ตั้งค่า Adapter พร้อม Callback
        adapter = ProductAdapter(productList) { product ->
            toggleFavorite(product)
        }
        recyclerView.adapter = adapter

        return view
    }

    // ✅ ฟังก์ชันจัดการการกดหัวใจ
    private fun toggleFavorite(product: ProductModel) {
        product.isFavorite = !product.isFavorite
        if (product.isFavorite) {
            FavoritesManager.addToFavorites(product) // ✅ เพิ่มสินค้าใน Favorites
        } else {
            FavoritesManager.removeFromFavorites(product) // ✅ ลบออกจาก Favorites
        }
        adapter.notifyDataSetChanged()
    }
}
