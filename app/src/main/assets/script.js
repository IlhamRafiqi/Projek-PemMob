/**
 * add event listener on multiple elements
 */

const addEventOnElements = function (elements, eventType, callback) {
  for (let i = 0, len = elements.length; i < len; i++) {
    elements[i].addEventListener(eventType, callback);
  }
}



/**
 * PRELOADER
 */

const preloader = document.querySelector("[data-preloader]");

window.addEventListener("load", function () {
  preloader.classList.add("loaded");
  document.body.classList.add("loaded");
});



/**
 * MOBILE NAVBAR
 */

const navbar = document.querySelector("[data-navbar]");
const navTogglers = document.querySelectorAll("[data-nav-toggler]");
const overlay = document.querySelector("[data-overlay]");

const toggleNav = function () {
  navbar.classList.toggle("active");
  overlay.classList.toggle("active");
  document.body.classList.toggle("nav-active");
}

addEventOnElements(navTogglers, "click", toggleNav);



/**
 * HEADER & BACK TOP BTN
 */

const header = document.querySelector("[data-header]");
const backTopBtn = document.querySelector("[data-back-top-btn]");

const activeElementOnScroll = function () {
  if (window.scrollY > 100) {
    header.classList.add("active");
    backTopBtn.classList.add("active");
  } else {
    header.classList.remove("active");
    backTopBtn.classList.remove("active");
  }
}

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
}

window.addEventListener("scroll", revealElementOnScroll);

window.addEventListener("load", revealElementOnScroll);


const counters = document.querySelectorAll(".counters span");
const container = document.querySelector(".counters");

let activated = false;

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

function getRandomScore() {
    return Math.floor(Math.random() * 101); // Skor acak antara 0 dan 100
}

function updateSpeedometer(score) {
    const rotationDegree = (score / 100) * 180 - 90; // Menghitung derajat rotasi jarum
    needle.style.setProperty('--score', score);
    needle.style.transform = `rotate(${rotationDegree}deg)`;
    scoreElement.textContent = score;

    // Update status berdasarkan skor
    const status = statusRanges.find(range => score <= range.max);
    statusElement.textContent = `${status.text}`;

    // Update status class based on score
    statusElement.className = 'status'; // Reset class
    statusElement.classList.add(status.class);
}

function update() {
    const score = getRandomScore();
    updateSpeedometer(score);
}

// Perbarui speedometer setiap 2 detik
setInterval(update, 2000);

// Inisialisasi speedometer saat halaman dimuat
update();

function updateSpeedometer(score) {
    const rotationDegree = (score / 100) * 180 - 90; // Menghitung derajat rotasi jarum
    needle.style.setProperty('--score', score);
    needle.style.transform = `rotate(${rotationDegree}deg)`;
    scoreElement.textContent = score;

    // Update status berdasarkan skor
    const status = statusRanges.find(range => score <= range.max);
    statusElement.textContent = `${status.text}`;

    // Update status class based on score
    statusElement.className = 'status'; // Reset class
    statusElement.classList.add(status.class);
}

const activePage = window.location.pathname;
const navLinks = document.querySelectorAll('.narbar-item .navbar-link').forEach(link => {
  if(link.href.includes(`${activePage}`)){
    link.classList.add('active');
    console.log(link);
  }
})