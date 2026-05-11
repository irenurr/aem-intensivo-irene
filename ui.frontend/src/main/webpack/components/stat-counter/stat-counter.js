(function(document) {
    "use strict";

    function initStatCounter() {
        const animateNumbers = (el) => {
            const targetAttr = el.getAttribute('data-target');
            if (!targetAttr) return;
            const target = parseInt(targetAttr);
            if (isNaN(target)) return;

            const duration = 2000;
            const stepTime = Math.abs(Math.floor(duration / target)) || 20;
            let current = 0;

            const timer = setInterval(() => {
                current += Math.ceil(target / 50) || 1;
                if (current >= target) {
                    el.innerText = target;
                    clearInterval(timer);
                } else {
                    el.innerText = current;
                }
            }, stepTime > 20 ? stepTime : 20);
        };

        const statObserver = new IntersectionObserver((entries, observer) => {
            entries.forEach(entry => {
                if (entry.isIntersecting) {
                    const numbers = entry.target.querySelectorAll('.js-stat-number');
                    numbers.forEach(num => animateNumbers(num));
                    observer.unobserve(entry.target);
                }      
            });
        }, { threshold: 0.5 });

        const statsContainer = document.querySelectorAll('.js-stats-container');
        if (statsContainer.length > 0) {
            statsContainer.forEach(container => {
                statObserver.observe(container);
            });
        }
    }

    if (document.readyState === "loading") {
        document.addEventListener('DOMContentLoaded', initStatCounter);
    } else {
        initStatCounter();
    }
}) (document);
