package com.example.sejenakapps

import android.content.Intent
import android.graphics.Color
import android.net.Uri
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.example.sejenakapps.adapter.ObatSliderAdapter
import com.example.sejenakapps.adapter.PsikologAdapter
import com.example.sejenakapps.adapter.RecommendedAdapter
import com.example.sejenakapps.adapter.RecommendedObatAdapter
import com.example.sejenakapps.model.ObatModel
import com.example.sejenakapps.model.PsikologModel

class InformasiActivity : AppCompatActivity() {

    private lateinit var viewPager: ViewPager2
    private lateinit var recyclerView: RecyclerView
    private lateinit var tabPsikolog: TextView
    private lateinit var tabObat: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_informasi)

        viewPager = findViewById(R.id.viewPagerPsikolog)

        recyclerView = findViewById(R.id.rvRecommended)
        tabPsikolog = findViewById(R.id.tabPsikolog)
        tabObat = findViewById(R.id.tabObat)

        // ===============================
        // Dummy Data Psikolog
        // ===============================
        val listPsikolog = listOf(
            PsikologModel(
                nama = "Dr. Lee Jeno, M.Psi., Psikolog",
                foto = R.drawable.jeno,
                lokasi = "Klinik Sejahtera Banyumas",
                deskripsi = "Dr. Lee Jeno adalah seorang Psikolog Klinis berpengalaman lebih dari 10 tahun dalam menangani gangguan kecemasan, stres pascatrauma, dan gangguan penyesuaian. Beliau menyelesaikan pendidikan Magister Profesi Psikologi di Universitas Indonesia dan aktif dalam berbagai kegiatan ilmiah di bidang kesehatan mental. Pendekatan terapinya berfokus pada keseimbangan emosional serta peningkatan kualitas hidup pasien melalui terapi kognitif dan perilaku."
            ),
            PsikologModel(
                nama = "Dr. Na Jaemin, M.Psi., Psikolog",
                foto = R.drawable.jaemin,
                lokasi = "Rumah Sakit Jiwa Bandung Raya",
                deskripsi = "Dr. Na Jaemin merupakan Psikolog Klinis dengan spesialisasi pada terapi perilaku kognitif (Cognitive Behavioral Therapy/CBT) dan manajemen stres kerja. Ia telah membantu banyak klien dalam mengatasi depresi, gangguan kecemasan sosial, serta burnout akibat tekanan pekerjaan. Selain praktik klinis, beliau aktif sebagai dosen tamu dan narasumber seminar mengenai kesehatan mental di berbagai institusi pendidikan."
            ),
            PsikologModel(
                nama = "Dr. Jennie Kim, M.Psi., Psikolog",
                foto = R.drawable.jennie,
                lokasi = "Klinik Harmoni Jakarta",
                deskripsi = "Dr. Jennie Kim adalah Psikolog yang berfokus pada bidang konseling keluarga dan hubungan interpersonal. Dengan pengalaman lebih dari 8 tahun, beliau banyak membantu pasangan dan keluarga dalam mengatasi konflik, meningkatkan komunikasi, serta memperkuat ikatan emosional. Dr. Jennie juga menulis berbagai publikasi ilmiah terkait terapi komunikasi efektif dan kesejahteraan emosional dalam hubungan."
            ),
            PsikologModel(
                nama = "Dr. Jang Wonyoung, M.Psi., Psikolog",
                foto = R.drawable.wonyong,
                lokasi = "Rumah Sakit Anak dan Remaja Surabaya",
                deskripsi = "Dr. Jang Wonyoung merupakan Psikolog Anak dan Remaja yang mendalami bidang perkembangan perilaku serta gangguan atensi dan hiperaktivitas (ADHD). Beliau memiliki pengalaman luas dalam menangani permasalahan tumbuh kembang anak serta memberikan pendampingan psikologis kepada remaja dengan kebutuhan khusus. Dikenal dengan pendekatan yang hangat dan empatik, beliau aktif bekerja sama dengan lembaga sosial dalam program edukasi kesehatan mental anak."
            )
        )


        // ===============================
        // Dummy Data Obat
        // ===============================
        val listObat = listOf(
            ObatModel(
                "Depram",
                "Mengobati depresi, kecemasan, dan gangguan panik",
                R.drawable.depram,
                "https://www.klikdokter.com/obat/obat-gangguan-saraf-pusat/depram"
            ),
            ObatModel(
                "Sandepril",
                "Mengatasi depresi ringan hingga berat",
                R.drawable.sandepril,
                "https://www.alodokter.com/sandepril?utm_source=chatgpt.com"
            ),
            ObatModel(
                "Alprazolam",
                "Meredakan kecemasan dan gangguan tidur",
                R.drawable.alprazolam,
                "https://www.alodokter.com/alprazolam?utm_source=chatgpt.com"
            ),
            ObatModel(
                "Frimania",
                "Mengatasi depresif pada gangguan bipolar",
                R.drawable.frimania,
                "https://www.klikdokter.com/obat/obat-gangguan-saraf-pusat/frimania"

            )


        )


        showPsikolog(listPsikolog)


        tabPsikolog.setOnClickListener {
            showPsikolog(listPsikolog)
            tabPsikolog.setTextColor(Color.parseColor("#2757A5"))
            tabObat.setTextColor(Color.parseColor("#9E9E9E"))
        }

        tabObat.setOnClickListener {
            showObat(listObat)
            tabObat.setTextColor(Color.parseColor("#2757A5"))
            tabPsikolog.setTextColor(Color.parseColor("#9E9E9E"))
        }
    }

    // ===============================
    // Fungsi menampilkan Psikolog
    // ===============================
    private fun showPsikolog(listPsikolog: List<PsikologModel>) {
        // Slider Psikolog
        viewPager.adapter = PsikologAdapter(listPsikolog)
        viewPager.orientation = ViewPager2.ORIENTATION_HORIZONTAL
        viewPager.isUserInputEnabled = true

        // RecyclerView Rekomendasi Psikolog
        recyclerView.layoutManager = GridLayoutManager(this, 2)
        recyclerView.adapter = RecommendedAdapter(listPsikolog, object : RecommendedAdapter.OnItemClickListener {
            override fun onShowClicked(psikolog: PsikologModel) {
                val intent = Intent(this@InformasiActivity, DetailPsikologActivity::class.java)
                intent.putExtra("nama", psikolog.nama)
                intent.putExtra("lokasi", psikolog.lokasi)
                intent.putExtra("deskripsi", psikolog.deskripsi)
                intent.putExtra("foto", psikolog.foto)
                startActivity(intent)
            }
        })
    }

    // ===============================
    // Fungsi menampilkan Obat
    // ===============================
    private fun showObat(listObat: List<ObatModel>) {
        viewPager.adapter = ObatSliderAdapter(listObat)
        viewPager.orientation = ViewPager2.ORIENTATION_HORIZONTAL
        viewPager.isUserInputEnabled = true

        recyclerView.layoutManager = GridLayoutManager(this, 2)
        recyclerView.adapter = RecommendedObatAdapter(listObat, object : RecommendedObatAdapter.OnItemClickListener {
            override fun onItemClicked(obat: ObatModel) {
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(obat.link))
                startActivity(intent)
            }
        })
    }
}
