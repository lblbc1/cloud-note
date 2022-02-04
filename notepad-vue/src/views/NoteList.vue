 <!--
 厦门大学计算机专业 | 前华为工程师
 专注《零基础学编程系列》https://cxyxy.blog.csdn.net/article/details/121134634
 包含：Java | 安卓 | 前端 | Flutter | iOS | 小程序 | 鸿蒙
 公众号：蓝不蓝编程
-->
<template>
  <div v-loading="loading2" element-loading-text="加载中" style="margin-left: 20px;margin-right: 20px;margin-top: 20px">
    <el-row style="padding:10px 15px;background:#fff">
      <el-col :span="24">
        <el-button size="small" type="primary" @click="addNote">新增</el-button>
      </el-col>
    </el-row>
    <el-row>
      <el-col :span='24'>
        <el-table
            style='width:100%' :data="articleLists" v-loading="listLoading"
            selection-change="selectChange(data)" element-loading-text="拼命加载中">
          <el-table-column type='index' width="60" label="序号"></el-table-column>
          <el-table-column prop="content" min-width="180" label="笔记"></el-table-column>
          <el-table-column min-width="60" label="操作">
            <template scope='scope'>
              <el-button size="small" type='primary' prop="id" @click="modifyNote(scope.row.id)">编辑</el-button>
              <el-button size="small" type='danger' @click="delNoteWithConfirmDialog(scope.row.id)">删除</el-button>
            </template>
          </el-table-column>
        </el-table>
        <!--        <div class="block" style="margin-top:10px;">-->
        <!--          <el-pagination-->
        <!--              @size-change="handleSizeChange"-->
        <!--              @current-change="handleCurrentChange"-->
        <!--              :current-page.sync="currentPage"-->
        <!--              :page-size="pageSize"-->
        <!--              layout="prev, pager, next, jumper"-->
        <!--              :total="totalNum" style="float:right">-->
        <!--          </el-pagination>-->
        <!--        </div>-->
      </el-col>
    </el-row>
  </div>
</template>
<script>
import {delNote, listNote} from "../api/note_api";

export default {
  data() {
    return {
      currentPage: 1,//当前选中的页码
      pageSize: 10,
      totalNum: 0,
      listLoading: true,
      articleLists: [],
      loading2: true,
    }
  },
  methods: {
    addNote() {
      this.$router.push({path: '/note/add'})
    },
    handleSizeChange(val) {
      console.log(`每页 ${val} 条`);
      this.listNote();
    },
    handleCurrentChange(val) {
      console.log(`当前页: ${val}`);
      this.listNote();
    },
    listNote() {
      let _this = this;
      this.listLoading = true;
      let queryUserId = this.$route.query.userId
      if (queryUserId === undefined) {
        queryUserId = ""
      }
      listNote(queryUserId).then(res => {
        this.loading2 = false;
        _this.listLoading = false;
        _this.articleLists = res.data.data;
      }).catch(error => {
        alert('发布失败');
        console.log(error);
      });
    },
    modifyNote(id) {
      this.$router.push({path: `/note/modify?id=${id}`})
    },
    delNoteWithConfirmDialog(id) {
      let userId = sessionStorage.getItem('user_id')
      if (userId === null) {
        this.$router.push("/note/login");
        return
      }

      this.$confirm('确定要删除么?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        this.delNote(id)
      }).catch(() => {
        this.$message({
          type: 'info',
          message: '已取消删除'
        });
      });
    },
    delNote(id) {
      delNote(id).then((res) => {
        this.$router.go(0)
      }).catch((e) => {
        this.$message({
          type: 'info',
          message: e
        });
      })
    }

  },
  mounted() {
    this.listNote();
  }
}
</script>
<style lang="scss" scoped>
.el-row {
  position: static;
}
</style>
