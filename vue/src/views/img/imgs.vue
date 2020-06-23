<template>

  <div>
    <div v-for="item in temps" :key="item.id" style="display: inline-flex;flex-direction: column;">
      <img class="img-share" style="width: 200px;height: 200px;border-radius: 5px;margin: 10px;"
           :src="item">
    </div>
  </div>

</template>

<script>
  import con1 from "@/assets/img/1.jpg";
  import con2 from "@/assets/img/2.jpg";
  import con3 from "@/assets/img/3.jpg";
  import con4 from "@/assets/img/4.jpg";

  export default {
    name: "imgs",
    data() {
      return {
        temps: [],
        listQuery: {
          pageNum: 1,//页码
          pageRow: 50,//每页条数
          name: ''
        },
      }
    },
    created(){
      this.getList();
    },
    methods: {
      getList(){
        if (!this.hasPerm('images:list')){
            return
        }
        this.api({
          url:'/images/listImages',
          method:"get",
          params:this.listQuery
        }).then(data => {
          this.temps = data.list;
          console.log('data.list的值',data.list);
        })
      }
    }
  }
</script>

<style scoped>
  .img-share {
    transition: all 0.3s;
  }

  .img-share:hover {
    transform: scale(1.2);
  }
</style>
