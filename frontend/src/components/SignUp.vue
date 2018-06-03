<template>
<div class="span3 well">
  <legend>New to WebApp? Sign up!</legend>
  <div>
    <input class="span3" v-model="user_id" placeholder="User ID" type="text"> 
    <input class="span3" v-model="user_pw" placeholder="User Password" type="password">
    <input class="span3" v-model="user_name" placeholder="User Name" type="text"> 
    <button class="btn btn-warning" type="button" @click="userSignup" @keyup.enter="userSignup">Sign up for WebApp</button>
  </div>
</div>
</template>

<script>
export default {
  name: 'Signup',
  data: function () {
    return {
      user_id: '',
      user_pw: '',
      user_name: ''
    }
  },
  methods: {
    userSignup: function () {
      this.axios
      .post('/user/signup', {
        id: this.user_id,
        pw: this.user_pw,
        name: this.user_name
      })
      .then(response => {
        if (response.data.success) {
          this.$router.push({ name: 'Main' })
        }
      })
      .catch(error => {
        console.log(error)
      })
    }
  }
}
</script>

<style scoped>

</style>
