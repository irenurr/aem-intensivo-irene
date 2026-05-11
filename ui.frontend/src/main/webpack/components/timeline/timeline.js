(function (document){
    "use strict";

    function initTimelineReveal () {
        const timelineObserver = new IntersectionObserver((entries, observer) => {
            entries.forEach(entry => {
                if (entry.isIntersecting) {
                    entry.target.classList.add('is-visible');
                    observer.unobserve(entry.target);              
                }      
            });
        }, { threshold: 0.5 });
        const timelineItems = document.querySelectorAll('.itv-timeline__item--reveal');
        if (timelineItems.length > 0) {
            timelineItems.forEach(item => {
                timelineObserver.observe(item);
            });
        }
    }
    if (document.readyState === "loading") {
        document.addEventListener('DOMContentLoaded', initTimelineReveal);
    } else {
        initTimelineReveal()
    }
      
})(document);