<template>
  <div class="app-container">
    <div class="filter-container">
      <el-form>
        <el-form-item>
          <el-button type="primary" icon="plus" @click="showCreate" v-if="hasPerm('news:add')">添加</el-button>
        </el-form-item>
      </el-form>
    </div>
    <el-table :data="list" v-loading:body="listLoading" element-loading-text="拼命加载中" border fit hightlight-current-row>
      <el-table-column align="center" label="序号" width="80">
        <template slot-scope="scope">
        <span v-text="getIndex(scope.$index)">

        </span>
        </template>
      </el-table-column>
      <el-table-column align="center" prop="content" label="新闻" width="140px"
                       style="flex-wrap: wrap;"></el-table-column>
      <el-table-column align="center" prop="file" label="内容" style="flex-wrap: wrap;"></el-table-column>
      <el-table-column align="center" label="创建时间" width="190">
        <template slot-scope="scope">
          <span>{{scope.row.createTime}}</span>
        </template>
      </el-table-column>
      <el-table-column align="center" label="管理" width="200" v-if="hasPerm('news:update')">
        <template slot-scope="scope">
          <el-button type="primary" icon="edit" @click="showUpdate(scope.$index)">修改</el-button>
        </template>
      </el-table-column>
    </el-table>
    <el-pagination @size-change="handleSizeChange" @current-change="handleCurrentChange"
                   :current-page="listQuery.pageNum"
                   :page-size="listQuery.pageRow" :total="totalCount" :page-sizes="[10,20,50,1000]"
                   layout="total,sizes,prev,pager,next,jumper">

    </el-pagination>
    <el-dialog :title="textMap[dialogStatus]" :visible.sync="dialogFormVisible">
      <el-form class="small-space" :model="tempNews" label-position="left" label-width="60px"
               style="width: 300px;margin-left: 50px;">
        <el-form-item label="新闻标题" label-width="70px">
          <el-input type="text" v-model="tempNews.content">

          </el-input>
        </el-form-item>
        <el-form-item label="内容">
          <el-input type="textarea" :rows="5" v-model="tempNews.file"></el-input>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogFormVisible=false">取消</el-button>
        <el-button v-if="dialogStatus == 'create'" type="success" @click="createNews">创建</el-button>
        <el-button type="primary" v-else @click="updateNews">修改</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
  export default {
    data() {
      return {
        totalCount: 0,
        list: [],
        listLoading: false,
        listQuery: {
          pageNum: 1,
          pageRow: 50,
          name: ''
        },
        dialogStatus: 'create',
        dialogFormVisible: false,
        textMap: {
          update: '编辑',
          create: '创建新闻'
        },
        tempNews: {
          id: "",
          content: "",
          file: ""
        }
      }
    },
    created() {
      this.getList();
    },
    methods: {
      getList() {
        if (!this.hasPerm('news:list')) {
          return;
        }
        this.listLoading = true;
        this.api({
          url: "/news/listNews",
          method: "get",
          params: this.listQuery
        }).then(data => {
          this.listLoading = false;
          this.list = data.list;
          this.totalCount = data.totalCount;
        })
      },
      handleSizeChange(val) {
        this.listQuery.pageRow = val
        this.handleFilter();
      },
      handleFilter() {
        //查询事件
        this.listQuery.pageNum = 1
        this.getList()
      },
      handleCurrentChange(val) {
        this.listQuery.pageNum = val
        this.getList();
      },
      getIndex($index) {
        return (this.listQuery.pageNum - 1) * this.listQuery.pageRow + $index + 1
      },
      showCreate() {
        this.dialogFormVisible = true;
        this.tempNews.content = "";
        this.tempNews.file = "";
        this.dialogStatus = "create";
      },
      showUpdate($index){
        this.tempNews.id = this.list[$index].id;
        this.tempNews.content = this.list[$index].content;
        this.tempNews.file = this.list[$index].file;
        this.dialogStatus = "update";
        this.dialogFormVisible = true
      },
      createNews() {
        this.api({
          url: "/news/addNews",
          method: "post",
          data: this.tempNews
        }).then(() => {
          this.getList();
          this.dialogFormVisible = false
        })
      },
      updateNews() {
        this.api({
          url: "/news/updateNews",
          method: "post",
          data: this.tempNews
        }).then(() => {
          this.getList();
          this.dialogFormVisible = false;
        })
      },
    }
  }
</script>

<style scoped>

</style>
