<template>
    <div>
        <img src="../assets/back.jpg" class="back-img">
        <h1 style="text-align: center; color: white;">欢迎使用OUC_Music</h1>
        <h3 class="index-bottom">有时间来听听音乐</h3>
        <h4 class="index-bottom-b">听听音乐真是一件美好的事</h4>
    </div>
</template>

<style>
*{
    user-select: none;
}
.back-img{
    position: absolute;
    width: 100%;
    height: 100%;
    top: 0px;
    bottom: 0px;
    left: 0px;
    right: 0px;
    user-select: none;
    -webkit-user-drag: none;
    z-index: -999;
}
.index-bottom{
    color: antiquewhite;
    text-align: center;
    bottom: 10%;
    position: absolute;
    width: 100%;
}
.index-bottom-b{
    color:burlywood;
    text-align: center;
    bottom: 6%;
    position: absolute;
    width: 100%;
}
</style>

<script>
    export default {}
</script>