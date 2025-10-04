/**
 * ==============================
 *  Sejenak App - scripts.js
 *  Versi Final Aman (WebView)
 * ==============================
 */

/**
 * Global error handler biar WebView gak crash
 */
window.onerror = function (msg, url, lineNo, columnNo, error) {
  console.error("⚠️ JS Error:", msg, "at", url, ":", lineNo);
  return true; // cegah WebView crash
};

/**
 * Tambah event listener ke banyak elemen
 */
const addEventOnElements = function (elements, eventType, callback) {
  for (let i = 0, len = elements.length; i < len; i++) {
    elements[i].addEventListener(eventType, callback);
  }
};

/**
 * PRELOADER
 */
const preloader = document.querySelector("[data-preloader]");
if (preloader) {
  window.addEventListener("load", function () {
    preloader.classList.add("loaded");
    document.body.classList.add("loaded");
  });
}

/**
 * MOBILE NAVBAR
 */
const navbar = document.querySelector("[data-navbar]");
const navTogglers = document.querySelectorAll("[data-nav-toggler]");
const overlay = document.querySelector("[data-overlay]");

const toggleNav = function () {
  if (navbar && overlay) {
    navbar.classList.toggle("active");
    overlay.classList.toggle("active");
    document.body.classList.toggle("nav-active");
  }
};

addEventOnElements(navTogglers, "click", toggleNav);

/**
 * HEADER & BACK TO TOP BUTTON
 */
const header = document.querySelector("[data-header]");
const backTopBtn = document.querySelector("[data-back-top-btn]");

const activeElementOnScroll = function () {
  if (!header || !backTopBtn) return;

  if (window.scrollY > 100) {
    header.classList.add("active");
    backTopBtn.classList.add("active");
  } else {
    header.classList.remove("active");
    backTopBtn.classList.remove("active");
  }
};

window.addEventListener("scroll", activeElementOnScroll);

/**
 * SCROLL REVEAL
 */
const revealElements = document.querySelectorAll("[data-reveal]");

const revealElementOnScroll = function () {
  for (let i = 0, len = revealElements.length; i < len; i++) {
    if (revealElements[i].getBoundingClientRect().top < window.innerHeight / 1.15) {
      revealElements[i].classList.add("revealed");
    } else {
      revealElements[i].classList.remove("revealed");
    }
  }
};

window.addEventListener("scroll", revealElementOnScroll);
window.addEventListener("load", revealElementOnScroll);

/**
 * COUNTERS (angka animasi)
 */
const counters = document.querySelectorAll(".counters span");
const container = document.querySelector(".counters");
let activated = false;

if (container) {
  window.addEventListener("scroll", () => {
    const containerTop = container.offsetTop;
    const containerHeight = container.offsetHeight;
    const windowBottom = window.pageYOffset + window.innerHeight;

    if (windowBottom > containerTop + containerHeight - 200 && !activated) {
      counters.forEach(counter => {
        counter.innerText = 0;
        let count = 0;

        function updateCount() {
          const target = parseInt(counter.dataset.count);
          if (count < target) {
            count++;
            counter.innerText = count;
            setTimeout(updateCount, 10);
          } else {
            counter.innerText = target;
          }
        }

        updateCount();
      });
      activated = true;
    }
  });
}

/**
 * SPEEDOMETER (status mental health)
 */
const needle = document.getElementById('needle');
const scoreElement = document.getElementById('score');
const statusElement = document.getElementById('status');

const statusRanges = [
  { max: 20, text: 'Bahagia', class: 'status-Bahagia' },
  { max: 40, text: 'Sehat', class: 'status-Sehat' },
  { max: 60, text: 'Depresi Ringan', class: 'status-Depresi-Ringan' },
  { max: 80, text: 'Depresi Sedang', class: 'status-Depresi-Sedang' },
  { max: 100, text: 'Depresi Berat', class: 'status-Depresi-Berat' }
];

function updateSpeedometer(score) {
  if (!needle || !scoreElement || !statusElement) return;

  const rotationDegree = (score / 100) * 180 - 90;
  needle.style.transform = `rotate(${rotationDegree}deg)`;
  scoreElement.textContent = score;

  const status = statusRanges.find(range => score <= range.max);
  if (status) {
    statusElement.textContent = status.text;
    statusElement.className = 'status';
    statusElement.classList.add(status.class);
  }
}

// tampilkan nilai awal (opsional)
updateSpeedometer(50);

/**
 * NAVBAR ACTIVE STATE
 */
const activePage = window.location.pathname;
document.querySelectorAll('.navbar-item .navbar-link').forEach(link => {
  if (link.href.includes(`${activePage}`)) {
    link.classList.add('active');
    console.log("Active link:", link.href);
  }
});

/**
 * BUBBLES (efek animasi gelembung)
 */
function initBubbles() {
  const sArray = [4, 6, 8, 10, 12, 14, 16];
  const bArray = [];

  for (let i = 0; i < window.innerWidth; i++) {
    bArray.push(i);
  }

  function randomValue(arr) {
    return arr[Math.floor(Math.random() * arr.length)];
  }

  setInterval(() => {
    const size = randomValue(sArray);
    const leftPos = randomValue(bArray);

    if (!$('.bubbles').length) return;

    $('.bubbles').append(
      `<div class="individual-bubble" style="left:${leftPos}px; width:${size}px; height:${size}px;"></div>`
    );

    $('.individual-bubble').animate(
      { bottom: '100%', opacity: '-=0.7' },
      4000,
      function () {
        $(this).remove();
      }
    );
  }, 600);
}

$(document).ready(function () {
  try {
    initBubbles();
  } catch (e) {
    console.error("Error in initBubbles():", e);
  }
});
