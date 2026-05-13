import Swiper from 'swiper';
import { Autoplay, Navigation } from 'swiper/modules';

import 'swiper/css';
import 'swiper/css/navigation';


const swipers = document.querySelectorAll('.js-cardlist-swiper');

if (swipers.length > 0) {
    swipers.forEach((Element) => {
        const isAutoplayEnabled = Element.getAttribute('data-autoplay') === 'true';
        const autoplayConfig = isAutoplayEnabled ? {
            delay: 5000,
                disableOnInteraction: false,
        } : false;
        new Swiper((Element), {
            modules: [Autoplay, Navigation],

            loop: true,
            autoplay: autoplayConfig,

            centeredSlides: false,
            slidesPerView: 'auto',
            grabCursor: true,

            spaceBetween: 24,

            breakpoints:{
                769: {
                    spaceBetween: 26,
                },
                1201: {
                    spaceBetween: 56,
                }
            },

            navigation: {
                nextEl: '.swiper-button-next',
                prevEl: '.swiper-button-prev',
            }
        });
    });
}