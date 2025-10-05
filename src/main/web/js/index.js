// 整体三条的画面
// 1. 获取DOM元素
const leftNav = document.querySelector('.content .left_nav');
const rightTopicArea = document.querySelector('.content .right_topic_area');
const middleContent = document.querySelector('.content .middle_content');

// 2. 计算并设置中间宽度的函数
function calculateMiddleWidth() {
    const leftWidth = parseFloat(getComputedStyle(leftNav).width);
    const rightWidth = parseFloat(getComputedStyle(rightTopicArea).width);
    const middleWidth = window.innerWidth - leftWidth - rightWidth;
    middleContent.style.width = `${middleWidth}px`;
}

// 3. 绑定事件，确保响应式
window.addEventListener('load', calculateMiddleWidth);
window.addEventListener('resize', calculateMiddleWidth);










// 注册登录界面
const screen = document.querySelector('#screen');
const container = document.querySelector('#container');
const signInButton = document.querySelector('#signIn');
const signUpButton = document.querySelector('#signUp');
const cancel = document.querySelector('.cancel');
const login = document.querySelector('#login');


signUpButton.addEventListener('click', () => container.classList.add('right-paner-active'));
signInButton.addEventListener('click', () => container.classList.remove('right-paner-active'));


cancel.addEventListener('click', () => {
    container.style.display = "none";
    cancel.style.display = "none";
    screen.style.display = "none";

})

login.addEventListener('click', () => {
    container.style.display = "block";
    cancel.style.display = "block";
    screen.style.display = "block";
})










// 单个帖子往返
const ul_longPost = document.querySelector('#longPost');
const singlePost = document.querySelector('#singlePost');
const ul_longPost_li_as = document.querySelectorAll('#longPost li a');
const singlePost_back = document.querySelector('#singlePost_back');


ul_longPost_li_as.forEach(function (ul_longPost_li_a) {
    ul_longPost_li_a.addEventListener('click', () => {
        ul_longPost.style.display = "none";
        singlePost.style.display = "block";
        // ul_longPost.style.opacity = "0";
        // singlePost.style.opacity = "1";
        // ul_longPost.style.zIndex = "1";
        // singlePost.style.zIndex = "2";

    })

})
singlePost_back.addEventListener('click', () => {
    ul_longPost.style.display = "block";
    singlePost.style.display = "none";
    // ul_longPost.style.opacity = "1";
    // singlePost.style.opacity = "0";
    // ul_longPost.style.zIndex = "2";
    // singlePost.style.zIndex = "1";
})



