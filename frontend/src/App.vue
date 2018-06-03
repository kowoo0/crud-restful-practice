<template>
  <div id="app">
    <router-view/>
    <router-link to="/">홈으로</router-link>
    <router-link to="/signup">회원가입</router-link>
    <router-link to="/login">로그인</router-link>
    <router-link to="/board/new">글쓰기</router-link>
  </div>
</template>

<script>
export default {
  name: 'App'
}
</script>

<style>
@import "assets/bootstrap/css/bootstrap.min.css";
</style>
