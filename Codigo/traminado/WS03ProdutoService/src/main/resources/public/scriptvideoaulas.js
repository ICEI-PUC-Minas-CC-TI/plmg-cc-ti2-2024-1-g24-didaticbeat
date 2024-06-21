
const adicionar = document.querySelector('#adicionar')

//Redirecionamento ao clicar em botões da navbar
const log = document.querySelector('#log');
const sign = document.querySelector('#sign');
const perfil = document.querySelector('#perfil')
const nav = document.querySelector('.navigation')
const AI = document.querySelector('#AI')
AI.addEventListener('click', function () {
  window.location.href = 'ai.html';
});

if (localStorage.getItem("userLogado")) {
  nav.removeChild(log.parentElement);
  nav.removeChild(sign.parentElement);
}
else {
  nav.removeChild(perfil.parentElement);
}

log.addEventListener('click', function () {
  window.location.href = 'forms.html';
});

sign.addEventListener('click', function () {
  window.location.href = 'forms.html';
});


perfil.addEventListener('click', function () {
  window.location.href = 'indexperfil.html';
});

adicionar.addEventListener('click', function () {
  window.location.href = 'indexcadastrovideoaulas.html';
});


import videos from "./videos.js";

function loadVideos() {
  const playlist_area = document.querySelector(".playlist");

  videos.forEach((video, index) => {
    const div = document.createElement("div");

    div.innerHTML = `
      <div class="playlist-video ${index + 1 === 1 && "active"}">
        <label class="playlist-video-info">${video.title}</label>
        <video src=${video.src} muted></video>
      </div>
    `;
    div.className = "index-video"

    playlist_area.appendChild(div);
  });

  addOnClick();
}

function addOnClick() {
  const video_main = document.querySelector(".main-video-content");
  const playlist_video = document.querySelectorAll(".playlist-video");

  playlist_video.forEach((item, i) => {
    if (!i) {
      setVideo(video_main, item);
    }

    item.onclick = () => {
      playlist_video.forEach((video) => video.classList.remove("active"));
      item.classList.add("active");

      setVideo(video_main, item);
    };
  });
}

function setVideo(video_main, item) {
  video_main.children[0].innerHTML = item.children[0].innerHTML;
  video_main.children[1].src = item.children[1].getAttribute("src");
}

loadVideos();



//Mensagem estilizada no console
console.log("\n╭━━━╮╱╱╭╮╱╱╭╮╱╱╱╱╱╭━━╮╱╱╱╱╱╱╭╮ \n╰╮╭╮┃╱╱┃┃╱╭╯╰╮╱╱╱╱┃╭╮┃╱╱╱╱╱╭╯╰╮\n╱┃┃┃┣┳━╯┣━┻╮╭╋┳━━╮┃╰╯╰┳━━┳━┻╮╭╯\n╱┃┃┃┣┫╭╮┃╭╮┃┃┣┫╭━╯┃╭━╮┃┃━┫╭╮┃┃ \n╭╯╰╯┃┃╰╯┃╭╮┃╰┫┃╰━╮┃╰━╯┃┃━┫╭╮┃╰╮\n╰━━━┻┻━━┻╯╰┻━┻┻━━╯╰━━━┻━━┻╯╰┻━╯\n                               ");
