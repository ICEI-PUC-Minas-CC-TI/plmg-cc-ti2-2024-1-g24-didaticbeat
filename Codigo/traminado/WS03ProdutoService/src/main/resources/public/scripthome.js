//Redirecionamento ao clicar em botões da navbar
const log = document.querySelector('#log');
const sign = document.querySelector('#sign');
const videoaulas = document.querySelector('#vid')
const perfil = document.querySelector('#perfil')
const adicionar = document.querySelector('#adicionar')
const nav = document.querySelector('.navigation')

if (localStorage.getItem("userLogado")) {
  nav.removeChild(log.parentElement);
  nav.removeChild(sign.parentElement);
}
else {
  nav.removeChild(perfil.parentElement);
}


const AI = document.querySelector('#AI')
AI.addEventListener('click', function () {
  window.location.href = 'ai.html';
});
log.addEventListener('click', function () {
  window.location.href = 'forms.html';
});

sign.addEventListener('click', function () {
  window.location.href = 'forms.html';
});

videoaulas.addEventListener('click', function () {
  window.location.href = 'indexvideoaulas.html';
});

perfil.addEventListener('click', function () {
  window.location.href = 'indexperfil.html';
});

// Obtém as referências para as divs
const di1 = document.querySelector('#di1');
const di2 = document.querySelector('#di2');
const di3 = document.querySelector('#di3');
const co1 = document.querySelector('#co1');
const co2 = document.querySelector('#co2');
const co3 = document.querySelector('#co3');
const ol = document.querySelector('.overlay');

// Oculta as divs de id "co(numero)"
co1.style.display = 'none';
co2.style.display = 'none';
co3.style.display = 'none';
ol.style.display = 'none';
co1.style.visibility = 'hidden';
co2.style.visibility = 'hidden';
co3.style.visibility = 'hidden';

// Adiciona os event listeners para as divs di1, di2 e di3
di1.addEventListener('click', function () {
  ol.style.display = 'inherit';
  co1.style.display = 'flex';
  co1.style.visibility = 'visible';
  co2.style.display = 'none';
  co2.style.visibility = 'hidden';
  co3.style.display = 'none';
  co3.style.visibility = 'hidden';
});

di2.addEventListener('click', function () {
  ol.style.display = 'inherit';
  co1.style.display = 'none';
  co1.style.visibility = 'hidden';
  co2.style.display = 'flex';
  co2.style.visibility = 'visible';
  co3.style.display = 'none';
  co3.style.visibility = 'hidden';
});

di3.addEventListener('click', function () {
  ol.style.display = 'inherit';
  co1.style.display = 'none';
  co1.style.visibility = 'hidden';
  co2.style.display = 'none';
  co2.style.visibility = 'hidden';
  co3.style.display = 'flex';
  co3.style.visibility = 'visible';
});

ol.addEventListener('click', function (evt) {
  if (!(evt.target === ol)) {
    return;
  }
  co1.style.display = 'none';
  co1.style.visibility = 'hidden';
  co2.style.visibility = 'hidden';
  co3.style.visibility = 'hidden';
  co2.style.display = 'none';
  co3.style.display = 'none';
  ol.style.display = 'none';
});



//Mensagem estilizada no console
console.log("\n╭━━━╮╱╱╭╮╱╱╭╮╱╱╱╱╱╭━━╮╱╱╱╱╱╱╭╮ \n╰╮╭╮┃╱╱┃┃╱╭╯╰╮╱╱╱╱┃╭╮┃╱╱╱╱╱╭╯╰╮\n╱┃┃┃┣┳━╯┣━┻╮╭╋┳━━╮┃╰╯╰┳━━┳━┻╮╭╯\n╱┃┃┃┣┫╭╮┃╭╮┃┃┣┫╭━╯┃╭━╮┃┃━┫╭╮┃┃ \n╭╯╰╯┃┃╰╯┃╭╮┃╰┫┃╰━╮┃╰━╯┃┃━┫╭╮┃╰╮\n╰━━━┻┻━━┻╯╰┻━┻┻━━╯╰━━━┻━━┻╯╰┻━╯\n                               ");

