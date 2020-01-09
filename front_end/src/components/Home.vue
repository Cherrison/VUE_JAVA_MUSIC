<template>
  <div class="home">
    <div class="header">
      <ul>
        <!-- 遍历数组初始化菜单 -->
        <!-- 匹配当前path的菜单项，样式修改为 'menu-active' -->
        <li
          v-for="(item, index) in menus"
          :key="index"
          :class="item.path == $route.path? 'menu-active': 'menu'"
          @click="onClick(item)"
        >
          {{ item.title }}
        </li>
      </ul>
    </div>
    <div class="main">
      <!-- 用path绑定组件的Key，解决带params路径跳转，页面不刷新的问题 -->
      <router-view :key="$route.path"></router-view>
    </div>
  </div>
</template>

<script>
  export default {
    data() {
      return {
        menus: [
          {
            title: '首页',
            path: '/'
          },
          {
            title: '关于',
            path: '/about'
          }
        ]
      }
    },
    methods: {
      onClick(item) {
        this.$router.push(item.path)
      }
    }
  }
</script>