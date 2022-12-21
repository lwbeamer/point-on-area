<template>
  <div id = "start-page">
    <table id ="main-table">
      <tr>
        <td id="header">
          <span class='left-aligned'>Осипов Василий Васильевич</span>
          <span class='right-aligned'>Вариант 1076<br/>Группа P3210</span>
        </td>
      </tr>
      <tr>
        <td>
          <h3 id ="clock-header">Текущее время и дата</h3>
          <div class = "datetime">
            <p>
              <span v-if="localeDate === 'Invalid Date'"></span>
              <span v-else id="date" class="date">{{localeDate}}</span>
              <br>
              <span v-if="localeTime === 'Invalid Date'"></span>
              <span v-else id="time" class="time">{{localeTime}}</span>
            </p>
          </div>
        </td>
      </tr>
      <tr>
        <td>
          <h3 id ="login-header">Регистрация и вход</h3>
          <form @submit.prevent>
            <input v-model.trim='username' id='login-input' required type='text' autocomplete='off' placeholder='Логин' maxlength='15'>
            <br>
            <input v-model.trim='password' id='password-input' required type='password' autocomplete='off' placeholder='Пароль' maxlength='15'>
            <br>
            <button @click = "login" id='login-button'>Вход</button>
            <button @click = "signup" id='signup-button'>Регистрация</button>
          </form>
          <hr id = "line">
        </td>
      </tr>
    </table>
  </div>
</template>

<script>
import axios from "axios";
import router from "@/router/router";

export default {
  data(){
    return{
      username: '',
      password: '',
      date: '',
      time: '',
      token: ''
    }
  },
  computed: {
    localeDate() {
      return (new Date(this.date)).toLocaleDateString()
    },
    localeTime(){
      return (new Date(this.time)).toLocaleTimeString()
    }
  },
  created() {
    this.intervalId = setInterval(() => this.date = Date.now(), 100);
    this.intervalId = setInterval(() => this.time = Date.now(), 100);
  },
  methods:{
    async login(){
      if (this.username === '' || this.password === ''){
        alert('Логин и пароль не могут быть пустыми')
        return
      }

      try{
        const user = {username: this.username, password: this.password}
        const response = await axios.post("/api/auth",user)
        const token = response.data.token
        const errorMessage = response.data.errorMessage
        if (token != null){
          localStorage.setItem('jwt', token)
          await router.push({name: 'main-page'})
        } else{
          alert(errorMessage)
        }
      } catch (e){
        alert("Произошла ошибка")
        console.log(e)
      }
    },
    async signup(){
      if (this.username === '' || this.password === ''){
        alert('Логин и пароль не могут быть пустыми')
        return
      }
      try{
        const user = {username: this.username, password: this.password}
        const response = await axios.post("/api/register",user)
        alert(response.data)
      } catch (e){
        alert("Произошла ошибка")
        console.log(e)
      }
    }
  },
  mounted() {
  }
}
</script>

<style>
#start-page{
  text-align: center;
}
table,tr,td{
  padding: 0px;
}

#main-table{
  width: 80%;
  margin: auto;
}
#header{
  background-color: #202225;
  color: whitesmoke;
  font-size: 17px;
}
.left-aligned{
  float: left;
  padding-top: 20px;
  padding-left: 15px;
}
.right-aligned{
  float: right;
  padding-right: 20px;
  padding-top: 10px;
  padding-bottom: 10px;
}
#clock-header{
  background-color: #202225;
  text-align: center;
  padding-bottom: 5px;
  padding-top: 7px;
}
#login-header{
  background-color: #202225;
  text-align: center;
  padding-bottom: 5px;
  padding-top: 7px;
}
.datetime{
  margin: auto;
  width: 35%;
  background-color: #3a2a45;
}
#date, #time{
  font-size: 40px;
}
#login-input{
  margin-bottom: 7px;
  font-family: 'Nunito', sans-serif;
  font-size: 18px;
  width: 25%;
}
#password-input{
  margin-bottom: 10px;
  font-family: 'Nunito', sans-serif;
  font-size: 18px;
  width: 25%;
}
#login-button{
  font-family: 'Nunito', sans-serif;
  font-size: 15px;
  margin-right: 12px;
}
#signup-button{
  font-family: 'Nunito', sans-serif;
  font-size: 15px;
}
#line{
  width: 27%;
}

/*телефоны*/
@media (max-width: 1187px) {
  .datetime{
    margin: auto;
    width: 50%;
    background-color: #3a2a45;
  }
  #date, #time{
    font-size: 30px;
  }

  #login-input{
    margin-bottom: 7px;
    font-family: 'Nunito', sans-serif;
    font-size: 18px;
    width: 45%;
  }
  #password-input{
    margin-bottom: 10px;
    font-family: 'Nunito', sans-serif;
    font-size: 18px;
    width: 45%;
  }

  #line{
    width: 50%;
  }

}

/*планшеты*/
@media (min-width: 819px)  and (max-width: 1187px){
  .datetime{
    margin: auto;
    width: 40%;
    background-color: #3a2a45;
  }
  #date, #time{
    font-size: 35px;
  }

  #login-input{
    margin-bottom: 7px;
    font-family: 'Nunito', sans-serif;
    font-size: 18px;
    width: 30%;
  }
  #password-input{
    margin-bottom: 10px;
    font-family: 'Nunito', sans-serif;
    font-size: 18px;
    width: 30%;
  }

  #line{
    width: 32%;
  }
}

</style>