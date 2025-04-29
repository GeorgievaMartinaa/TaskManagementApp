const swiper = new Swiper('.swiper', {
    // Optional parameters
    direction: 'horizontal',
    loop: false,
    slidesPerView: 1,
    breakpoints: {
        640: {
            slidesPerView: 2,
        },
        1024: {
            slidesPerView: 3,
        },
    },
    // Navigation arrows
    navigation: {
        nextEl: '.swiper-button-next',
        prevEl: '.swiper-button-prev',
    },
});

const taskContainer = document.getElementById('card-tasks');

const resizeObserver = new ResizeObserver(()=> {
    showTasks();
});

resizeObserver.observe(taskContainer)


function showTasks() {
    const tasks = taskContainer.querySelectorAll('li');

    let totalHeight = 0; //how much height is occupied
    let containerHeight = taskContainer.offsetHeight; // total available height
    let limitIndex = tasks.length; // on default show all list items

    console.log(containerHeight);

    //count how much elements can be shown
    tasks.forEach((task, index) => {
        task.style.display = 'block';
        console.log(task.offsetHeight);
        totalHeight += task.offsetHeight;
        if (totalHeight <= containerHeight) {
            limitIndex = index;
        } else {
            return false;
        }
    });

    // show/hide the elements
    tasks.forEach((task, index) => {
        if (index <= limitIndex) {
            task.style.display = 'block';
        }else {
            task.style.display='none';
        }
    });
}


