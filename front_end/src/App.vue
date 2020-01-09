<template>
  <div id="app">
    <router-view class="margin-header" />
  </div>
</template>

<style>
@import "/assets/css/bootstrap.min.css";
@import "/assets/css/index.css";
#app {
  font-family: 'Avenir', Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  color: #2c3e50;
}
/* .margin-header{
  margin-top: 50px;
} */
@media only screen and (max-width: 878px) {
    .margin-header{
      margin-top: 100px;
    }
}
</style>

<script>
import Home from '@/pages/Main'
export default {
  name: 'App',
  components: {
      Home
  }
}
</script>