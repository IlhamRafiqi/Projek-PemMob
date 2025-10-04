// =====================================================
//  Sejenak App - scripts.js (Final Android Safe Version)
// =====================================================

//  Semua fungsi diinisialisasi di dalam satu fungsi utama
function initParadoxWay() {
    "use strict";

    // -------------------------------
    // Swiper (untuk carousel / slider)
    // -------------------------------
    if ($(".testimonials-carousel").length > 0) {
        var j2 = new Swiper(".testimonials-carousel .swiper-container", {
            preloadImages: false,
            slidesPerView: 1,
            spaceBetween: 20,
            loop: true,
            grabCursor: true,
            mousewheel: false,
            centeredSlides: true,
            pagination: {
                el: '.tc-pagination',
                clickable: true,
                dynamicBullets: true,
            },
            navigation: {
                nextEl: '.listing-carousel-button-next',
                prevEl: '.listing-carousel-button-prev',
            },
            breakpoints: {
                1024: {
                    slidesPerView: 3,
                },
            }
        });
    }

    // ----------------------------------
    // Bubbles animation (gelembung efek)
    // ----------------------------------
    var sArray = [4, 6, 8, 10, 12, 14, 16]; // ukuran gelembung
    var bArray = []; // posisi horizontal (nanti diisi otomatis)

    for (var i = 0; i < $(window).width(); i++) {
        bArray.push(i); // isi posisi kiri dari 0 sampai lebar layar
    }

    // Fungsi untuk ambil nilai acak dari array
    function randomValue(arr) {
        return arr[Math.floor(Math.random() * arr.length)];
    }

    // Buat efek bubble tiap 600ms biar gak berat
    setInterval(function () {
        var size = randomValue(sArray);
        var leftPos = randomValue(bArray);

        // Tambahkan bubble ke elemen .bubbles (pastikan ada di HTML)
        $('.bubbles').append(
            '<div class="individual-bubble" style="left:' +
            leftPos +
            'px; width:' +
            size +
            'px; height:' +
            size +
            'px;"></div>'
        );

        // Animasikan bubble ke atas
        $('.individual-bubble').animate(
            {
                'bottom': '100%',
                'opacity': '-=0.7'
            },
            4000,
            function () {
                $(this).remove();
            }
        );
    }, 600);
}

// --------------------------------------
// Jalankan fungsi utama saat dokumen siap
// --------------------------------------
$(document).ready(function () {
    try {
        initParadoxWay();
    } catch (e) {
        console.error("Error in initParadoxWay():", e);
    }
});
