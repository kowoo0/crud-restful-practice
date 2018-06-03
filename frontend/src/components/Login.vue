<template>
<div class="span3 well">
  <legend>Login</legend>
  <div>
    <input class="span3" v-model="user_id" placeholder="User ID" type="text"> 
    <input class="span3" v-model="user_pw" placeholder="User Password" type="password">
    <button class="btn btn-warning" type="button" @click="userLogin" @keyup.enter="userLogin">Login</button>
  </div>
</div>
</template>

<script>
export default {
  name: 'Login',
  data: function () {
    return {
      user_id: '',
      user_pw: ''
    }
  },
  methods: {
    userLogin: function () {
      this.axios
      .post('/user/login', {
        id: this.user_id,
        pw: this.user_pw
      })
      .then(response => {
        console.log(response)
        this.$router.push({ name: 'Main' })
      })
      .catch(error => {
        console.log(error)
      })
    }
  }
}
</script>

<style>

</style>
