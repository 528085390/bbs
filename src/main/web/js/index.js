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