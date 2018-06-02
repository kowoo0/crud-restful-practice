<template>
  <div class="main">
    <h1>{{ msg }}</h1>
    <button @click="getList">get all</button>
    <ul>
      <li v-for="user in users" :key="user.no">
        {{ user.name }}
      </li>
    </ul>
  </div>
</template>

<script>
export default {
  name: 'Main',
  data () {
    return {
      msg: 'Welcome to Vue.js + Springboot',
      users: []
    }
  },
  mounted () {

  },
  methods: {
    getList () {
      this.axios
      .get('/user/list')
      .then(res => {
        if(res.data.success) {
          this.users = res.data.users
        }
        console.log(res)
      })
      .catch(err => {
        console.log(err)
      })
    }
  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>

</style>
