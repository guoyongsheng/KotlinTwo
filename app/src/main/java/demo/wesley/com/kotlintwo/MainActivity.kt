package demo.wesley.com.kotlintwo

import android.content.Context
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.widget.Toast
import com.google.gson.Gson
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread
import java.net.URL

fun MainActivity.toast(context: Context, msg: String, duration: Int = Toast.LENGTH_SHORT) {
    Toast.makeText(context, msg, duration).show()
}

class MainActivity : AppCompatActivity(), MainAdapter.OnItemClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        init()
    }

    //初始化
    private fun init() {

        val recyclerView: RecyclerView = findViewById(R.id.recycler) as RecyclerView

        //layoutManager
        val layoutManager: LinearLayoutManager = LinearLayoutManager(this)
        recyclerView.layoutManager = layoutManager

        //divider
        val divider: DividerItemDecoration = DividerItemDecoration(this, DividerItemDecoration.VERTICAL)
        recyclerView.addItemDecoration(divider)

        //adapter
        val adapter: MainAdapter = MainAdapter(this, getDataSource())
        adapter.setOnItemClickListener(this)
        recyclerView.adapter = adapter

        adapter.addAllItems(getDataSource())



        doAsync {
            val gson = Gson()
            val value = URL("http://www.izaodao.com/Api/AppFiftyToneGraph/videoLink/?once_nos=true").readText()
            //val response: Response<List<ItemInfo>> = gson.fromJson(value, Response<List<ItemInfo>>::class.java)
            uiThread {
                Log.e(" value = ", value)
            }
        }
    }

    private fun getDataSource(): MutableList<String> {
        val list: MutableList<String> = mutableListOf(
                "android",
                "kotlin",
                "retrofit",
                "rxjava",
                "okhttp",
                "glide",
                "dagger",
                "mvp",
                "material design"
        )
        return list
    }

    override fun onItemClick(position: Int) {
        toast(this, position.toString())
    }
}
