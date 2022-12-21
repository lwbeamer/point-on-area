<template>
<template>
  <table id='main-table' v-cloak>
    <tr>
      <td id='header' colspan="2">
        <span class='left-aligned'>Осипов Василий Васильевич</span>
        <span class='right-aligned'>Вариант 1076<br/>Группа P3210</span>
      </td>
    </tr>
    <tr>
      <td id = 'graph-table-square'>
        <div class = 'graph-header'>
          <h2 class = 'graph-header-title'>График</h2>
        </div>
        <div class = 'image-container'>
          <svg width="220" height="220" class="areas" xmlns="http://www.w3.org/2000/svg">

            <!-- X-Axis -->
            <line class="axis" x1="10" y1="110" x2="210" y2="110" stroke="black" />
            <polygon points="210,110 200,105 200,115"/>

            <!-- Y-Axis -->
            <line class="axis" x1="110" y1="10" x2="110" y2="210" stroke="black" />
            <polygon points="110,10 105,20 115,20"/>

            <!-- X-Axis coordinates-->
            <line class="coordinate-line" id="x-division1" x1="42" y1="105" x2="42" y2="115" stroke="black" />
            <text class="coordinate-text coordinate-text_minus-Rx" x="33" y="100" font-size="14">-R</text>

            <line class="coordinate-line" id="x-division2" x1="76" y1="105" x2="76" y2="115" stroke="black" />
            <text class="coordinate-text coordinate-text_minus-half-Rx" x="68" y="100" font-size="14">-R/2</text>

            <line class="coordinate-line" id="x-division3" x1="144" y1="105" x2="144" y2="115" stroke="black" />
            <text class="coordinate-text coordinate-text_plus-half-Rx" x="138" y="100" font-size="14">R/2</text>

            <line class="coordinate-line" id="x-division4" x1="178" y1="105" x2="178" y2="115" stroke="black" />
            <text class="coordinate-text coordinate-text_plus-Rx" x="171" y="100" font-size="14">R</text>

            <!-- Y-Axis coordinates-->
            <line class="coordinate-line" x1="105" y1="178" x2="115" y2="178" stroke="black" />
            <text class="coordinate-text coordinate-text_minus-Ry" x="120" y="181" font-size="14">-R</text>

            <line class="coordinate-line" x1="105" y1="144" x2="115" y2="144" stroke="black" />
            <text class="coordinate-text coordinate-text_minus-half-Ry" x="120" y="148" font-size="14">-R/2</text>

            <line class="coordinate-line" x1="105" y1="76" x2="115" y2="76" stroke="black" />
            <text class="coordinate-text coordinate-text_plus-half-Ry" x="120" y="81" font-size="14">R/2</text>

            <line class="coordinate-line" x1="105" y1="42" x2="115" y2="42" stroke="black" />
            <text class="coordinate-text coordinate-text_plus-Ry" x="120" y="48" font-size="14">R</text>

            <!-- Triangle -->
            <polygon class="svg-figure triangle-figure" points="178,110 110,144 110,110"
                     fill="#5662F6" fill-opacity="0.25" stroke="#5662F6" stroke-opacity="0.5" />

            <!-- Rectangle -->
            <!--    возможно надо поменять 42 на 43-->
            <polygon class="svg-figure rectangle-figure" points="178,110 178,42 110,42 110,110"
                     fill="#5662F6" fill-opacity="0.25" stroke="#5662F6" stroke-opacity="0.5" />

            <!-- Circle -->
            <path class="svg-figure circle-figure" d="
                M 43 110
                A 68 68 0 0 0 110 178
                L 110 110 Z"
                  fill="#5662F6" fill-opacity="0.25" stroke="#5662F6" stroke-opacity="0.5"/>
          </svg>
          <canvas @click="canvasHandler" ref="canvas" id = "canvas" :width="canvasWidth" height="220">Интерактивный элемент не поддерживается</canvas>
        </div>
      </td>
      <td id ='values-table-square'>
        <div class = "values-header">
          <h2 class ='values-header-title'>Значения</h2>
        </div>
        <div class ='values-container'>
          <form id = 'input-form' @submit.prevent>
            <table id = 'input-table'>
              <tr>
                <td class='input-label'>
                  <h3>X: </h3>
                </td>
                <td class = 'input-value'>
                  <input @change = 'changeX' ref="x-input" :class="{'text-error': xError}" id='x-textinput' type='text' name='xvalue' autocomplete='off' placeholder='Число от -3 до 3...' maxlength='10'>
                </td>
              </tr>
              <tr>
                <td class='input-label'>
                  <h3>Y: </h3>
                </td>
                <td class='input-value'>
                  <input @change = 'changeY' ref="y-input" :class="{'text-error': yError}" id='y-textinput' type='text' name='yvalue' autocomplete='off' placeholder='Число от -5 до 5...' maxlength='10'>
                </td>
              </tr>
              <tr>
                <td class='input-label'>
                  <h3>R: </h3>
                </td>
                <td class='input-value'>
                  <input @change = 'changeR' ref="r-input" :class="{'text-error': rError}" id='r-textinput' type='text' name='rvalue' autocomplete='off' placeholder='Число от 0 до 3...' maxlength='10'>
                </td>
              </tr>
              <tr>
                <td colspan='2'>
                  <div class='sub-res-buttons'>
                    <button class="sub-button" @click="sendPoint">Принять</button>
                    <button class="res-button" @click="deletePoints" >Сброс</button>
                    <button @click = "logout" class="logout-button">Выйти</button>
                  </div>
                </td>
              </tr>
            </table>
          </form>
        </div>
      </td>
    </tr>
    <tr>
      <td 	id='result-table-square'  colspan="2">
        <div class = 'result-header'>
          <h2 class = 'result-header-title'>Таблица</h2>
        </div>
        <div class = 'result-content'>
          <p id = "no-points" v-if="points.length === 0">Точек нет...</p>
          <table v-else id = 'result-content-table'>
            <tr id='table-header'>
              <th class='coords'>X</th>
              <th class='coords'>Y</th>
              <th class='coords'>R</th>
              <th>Текущее время</th>
              <th>Время выполнения</th>
              <th>Результат</th>
            </tr>
            <tr v-for="point in points" :key="point">
              <td>{{ point.xValue }}</td>
              <td>{{ point.yValue }}</td>
              <td>{{ point.rValue }}</td>
              <td>{{ point.currentTime}}</td>
              <td>{{ point.executionTime }}</td>
              <td>{{ point.hitResult}}</td>
            </tr>
          </table>
        </div>
      </td>
    </tr>
  </table>
</template>

<script>
import axios from "axios";
import router from "@/router/router";

import svgAreas from '@/assets/svgareas.svg';
import token from "@/pages/StartPage";

export default {
  // name: "MainPage.vue"
  data(){
    return{

      svgAreas: require('@/assets/svgareas.svg'),
      canvasWidth: 220,
      numValueX: undefined,
      numValueY: undefined,
      numValueR: undefined,
      xError: false,
      yError: false,
      rError: false,
      X_MIN: -3,
      X_MAX: 3,
      Y_MIN: -5,
      Y_MAX: 5,
      R_MIN: 0,
      R_MAX: 3,
      points: new Array(0)
    }
  },
  methods:{
    logout(){
      localStorage.clear();
      router.push({name:"start-page"})
    },

    canvasHandler(event){
      if (this.numValueR === undefined){
        alert("Установите R")
        return
      }

      let svgGraph = document.querySelector('.areas');

      let doubleDivisionRange = svgGraph.querySelector('#x-division3').getAttribute("x1")-
          svgGraph.querySelector('#x-division2').getAttribute("x1");

      let halfCanvas = this.canvasWidth/2;

      this.numValueX = (event.offsetX - halfCanvas)/doubleDivisionRange * this.numValueR;
      this.numValueY = (-event.offsetY + halfCanvas)/doubleDivisionRange * this.numValueR;

      if (this.numValueX<this.X_MIN) this.numValueX = this.X_MIN + 0.00000000001;
      if (this.numValueX>this.X_MAX) this.numValueX = this.X_MAX - 0.00000000001;

      if (this.numValueY<this.Y_MIN) this.numValueY = this.Y_MIN + 0.00000000001;
      if (this.numValueY>this.Y_MAX) this.numValueY = this.Y_MAX - 0.00000000001;



      this.numValueX = this.numValueX.toFixed(7)
      this.numValueY = this.numValueY.toFixed(7)

      this.sendPoint()

    },

    drawAllPoints(){
      let svgGraph = document.querySelector('.areas');

      let doubleDivisionRange = svgGraph.querySelector('#x-division3').getAttribute("x1")-
          svgGraph.querySelector('#x-division2').getAttribute("x1");

      let halfCanvas = this.canvasWidth/2;

      this.points.forEach(point => {
        let x = point.xValue
        let y = point.yValue
        let result = point.hitResult
        this.drawPoint((x/this.numValueR * doubleDivisionRange + halfCanvas), -(y / this.numValueR * doubleDivisionRange - halfCanvas), result);
      })

    },

    drawPoint(x,y,result){
      let ctx = this.$refs.canvas.getContext('2d')
      ctx.beginPath();
      ctx.arc(x,y,2,0,Math.PI * 2);
      if (result) {
        ctx.fillStyle = 'green'
      } else {
        ctx.fillStyle = 'red';
      }
      ctx.fill();
    },

    deletePoints(){
      axios.delete("/api/points/delete",
          {headers: {Authorization: "Bearer " + localStorage.getItem("jwt")}
          }).then(response => {
        this.clearCanvas()
        this.loadPoints()
      }).catch(e => {
        if (e.response.status === 403){
          alert("Некорректный или устаревший токен. Выполните вход повторно")
          router.push({name: 'start-page'})
        }
        console.log('Error!')
      });
    },

    clearCanvas(){
      this.$refs.canvas.getContext('2d').clearRect(0, 0, this.$refs.canvas.width, this.$refs.canvas.height);
    },

    isNumber(s){
      if (s === 0) return true;
      return (!isNaN(parseFloat(s)) && s);
    },

    changeX(event){
      let fieldValue = event.target.value.trim();
      fieldValue = fieldValue.replace(',', '.');
      let checkingValue = parseFloat(fieldValue)
      if (checkingValue === +fieldValue && this.isNumber(checkingValue)){
        if (checkingValue>this.X_MIN && checkingValue<this.X_MAX){
          this.numValueX = checkingValue
          this.xError = false;
        } else{
          this.numValueX = undefined
          this.xError = true;
        }
      } else{
        this.numValueX = undefined
        this.xError = true;
      }
    },

    changeY(event){
      let fieldValue = event.target.value.trim();
      fieldValue = fieldValue.replace(',', '.');
      let checkingValue = parseFloat(fieldValue)
      if (checkingValue === +fieldValue && this.isNumber(checkingValue)){
        if (checkingValue>this.Y_MIN && checkingValue<this.Y_MAX){
          this.numValueY = checkingValue
          this.yError = false;
        } else{
          this.numValueY = undefined
          this.yError = true;
        }
      } else{
        this.numValueY = undefined
        this.yError = true;
      }
    },

    changeR(event){
      let fieldValue = event.target.value.trim();
      fieldValue = fieldValue.replace(',', '.');
      let checkingValue = parseFloat(fieldValue)
      if (checkingValue === +fieldValue && this.isNumber(checkingValue)){
        if (checkingValue>this.R_MIN && checkingValue<this.R_MAX){
          this.numValueR = checkingValue
          this.rError = false;

          let svgGraph = document.querySelector('.areas');
          svgGraph.querySelector('.coordinate-text_minus-Rx').textContent = (-this.numValueR).toString();
          svgGraph.querySelector('.coordinate-text_minus-Ry').textContent = (-this.numValueR).toString();
          svgGraph.querySelector('.coordinate-text_minus-half-Rx').textContent = (-this.numValueR/2).toString();
          svgGraph.querySelector('.coordinate-text_minus-half-Ry').textContent = (-this.numValueR/2).toString();
          svgGraph.querySelector('.coordinate-text_plus-Rx').textContent = (this.numValueR).toString();
          svgGraph.querySelector('.coordinate-text_plus-Ry').textContent = (this.numValueR).toString();
          svgGraph.querySelector('.coordinate-text_plus-half-Rx').textContent = (this.numValueR/2).toString();
          svgGraph.querySelector('.coordinate-text_plus-half-Ry').textContent = (this.numValueR/2).toString();

          this.clearCanvas();
          this.drawAllPoints();

        } else{
          this.numValueR = undefined
          this.rError = true;
        }
      } else{
        this.numValueR = undefined
        this.rError = true;
      }
    },

    sendPoint(){

        const point = {
          xValue: this.numValueX,
          yValue: this.numValueY,
          rValue: this.numValueR,
        }

      let svgGraph = document.querySelector('.areas');

      let doubleDivisionRange = svgGraph.querySelector('#x-division3').getAttribute("x1")-
          svgGraph.querySelector('#x-division2').getAttribute("x1");

      let halfCanvas = this.canvasWidth/2;


        axios.post('/api/points/add', point, {headers: {"Authorization": "Bearer " + localStorage.getItem("jwt")}})
        .then(response => {
          if (!(typeof response.data == "object")) return;
          this.points.push(response.data)
          this.drawPoint((response.data.xValue/this.numValueR * doubleDivisionRange + halfCanvas), -(response.data.yValue / this.numValueR * doubleDivisionRange - halfCanvas), response.data.hitResult)
          this.$refs["x-input"].value = response.data.xValue
          this.$refs["y-input"].value = response.data.yValue
        }).catch(e=>{
          if (e.response.status === 403){
            alert("Некорректный или устаревший токен. Выполните вход повторно")
            router.push({name: 'start-page'})
          }
          console.log('Error!')
        });
    },

    loadPoints(){
        axios.get('/api/points/get/all', {headers: {"Authorization": "Bearer " + localStorage.getItem("jwt")}})
        .then(response => {
          this.points = response.data;
        }).catch(e=>{
          if (e.response.status === 403){
            alert("Некорректный или устаревший токен. Выполните вход повторно")
            router.push({name: 'start-page'})
          }
          if (e.response.status === 400){
            alert("Невозможно загрузить точки. Попробуйте выполнить вход повторно")
            router.push({name: 'start-page'})
          }
          console.log('Error!')
        });
      }
  },
  mounted() {
    this.loadPoints();
  }
}
</script>

<style scoped>
[v-cloak]{
  display: none;
}

table,tr,td{
  padding: 0;
}
#main-table{
  width: 100%;
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

#result-table-square{
  width: 100%;
  position: relative;
  vertical-align: top;
}

.result-header{
  background-color: #202225;

}
.result-header-title{
  text-align: center;
  margin-bottom: 0;
  padding-bottom: 5px;
}

#result-content-table{
  width: 100%;
  position: relative;
  background-color: #AFB1B5;
  text-align: center;
}

.result-content-table td{
  border: 1px solid #333946;
  border-collapse: collapse;
}

.result-content-table th{
  border: 2px solid #202225;
  border-collapse: collapse;
}

.result-content{
  position: absolute;
  height: 350px;
  width: 100%;
  overflow-y: scroll;
  background-color: #36393F;
}
#result-content-table tr:nth-child(2n-1) {
  background-color: #41444A;
}

#result-content-table tr:nth-child(2n) {
  background-color: #41444A;
}

#result-content-table th:nth-child(-n+3) {
  width: 15%;
}

#no-points{
  font-size: 20px;
  position: center;
  margin-left: 15px
}
#graph-table-square{
  vertical-align: top;
}
.graph-header{

  background-color: #202225;
}
.values-header{
  background-color: #202225;
}
.graph-header-title{
  text-align: center;
  padding-bottom: 5px;
  margin-bottom: 0;
}
.image-container{
  position: relative;
  text-align: center;
  background-color: #FFFFFF;
  padding-bottom: 19px;
}
.graph-image{
  height: 250px;
  /*margin-left: 50px;*/
}
#values-table-square{
  vertical-align: top;
}

.values-header{

}

.values-header-title{
  text-align: center;
  padding-bottom: 5px;
  margin-bottom: 0;

}
.values-container{
  background-color: #36393F;

}
.input-label{
  width: 25%;
}
.input-value{
  text-align: center;
}
#input-table{
  margin: auto;
}

#x-textinput, #y-textinput, #r-textinput{
  width: 95%;
  font-family: 'Nunito', sans-serif;
  font-size: 15px;
}

.sub-button{
  font-family: 'Nunito', sans-serif;
  font-size: 15px;
  margin-right: 7px;
}

.res-button{
  font-family: 'Nunito', sans-serif;
  font-size: 15px;
  margin-right: 7px;
}

.logout-button{
  font-family: 'Nunito', sans-serif;
  font-size: 15px;
}

.sub-res-buttons{
  text-align: center;
  margin-bottom: 12px;
  padding-top: 7px;
  border-top: solid whitesmoke 3px;
}

#canvas{
  position: absolute;
  left: 0;
  right: 0;
  margin: 0 auto;
}
.text-error{
  border: 2px solid #ff0000;
  color: red;
}
.text-error::placeholder{
  color: #ff0000;
}

/*телефоны*/
@media (max-width: 1187px) {

  #result-content-table{
    font-size: 12px;
    width: 100%;
    position: relative;
    background-color: #AFB1B5;
    text-align: center;
  }
}

/*планшеты*/
@media (min-width: 819px)  and (max-width: 1187px){
  #result-content-table{
    font-size: 20px;
    width: 100%;
    position: relative;
    background-color: #AFB1B5;
    text-align: center;
  }
}

</style>