<template>
  <div id="category-tag">
    <p>分类/标签</p>
    <div class="category-tag-wrap">
      <div class="table-wrap">
        <div class="action-btn-wrap">
          <span @click="newCategory">新增分类</span>
        </div>
        <el-table :data="catetoryList" border style="width: 100%">
          <el-table-column prop="name" label="标签名称" width="100"></el-table-column>
          <el-table-column prop="articleCount" label="文章数" width="80"></el-table-column>
          <el-table-column prop="createTime" label="创建时间" width="112"></el-table-column>
          <el-table-column prop="updateTime" label="更新时间" width="115"></el-table-column>
          <el-table-column prop="status" label="状态" width="100"></el-table-column>
          <el-table-column  label="操作" width="120">
            <template slot-scope="scope">
              <el-button @click="handleClick(scope.row)" type="text" size="small">查看</el-button>
              <el-button type="text" size="small">编辑</el-button>
            </template>
          </el-table-column>
        </el-table>
      </div>
      <div class="table-wrap">
        <div class="action-btn-wrap">
          <span @click="newTag">新增标签</span>
        </div>
        <el-table :data="tagList" border style="width: 100%">
          <el-table-column prop="name" label="标签名称" width="100"></el-table-column>
          <el-table-column prop="articleCount" label="文章数" width="80"></el-table-column>
          <el-table-column prop="createTime" label="创建时间" width="112"></el-table-column>
          <el-table-column prop="updateTime" label="更新时间" width="115"></el-table-column>
          <el-table-column prop="status" label="状态" width="100"></el-table-column>
          <el-table-column  label="操作" width="120">
            <template slot-scope="scope">
              <el-button @click="handleClick(scope.row)" type="text" size="small">查看</el-button>
              <el-button type="text" size="small">编辑</el-button>
            </template>
          </el-table-column>
        </el-table>
      </div>
    </div>
  </div>
</template>


<script>
import { mapActions, mapGetters } from "vuex";
import moment from "moment";

export default {
  name: "category-tag",
  components: {},
  data() {
    return {
      catetoryList: [],
      tagList: []
    };
  },
  created() {
    this.getCList();
    this.getTList();
  },
  methods: {
    ...mapActions([
      "getCategoryList",
      "getTagList",
      "addCategory",
      "addTag",
      "modifyCategory",
      "modifyTag",
      "deleteCategory",
      "deleteTag"
    ]),
    formatTime(row, column, cellValue, index) {
      return cellValue
        ? moment(parseInt(cellValue) * 1000).format("YYYY-MM-DD HH:ss")
        : "-";
    },
    formatStatus(value) {
      return value == "0" ? "使用中" : "已删除";
    },
    editTag(tag) {
      this.showDialogWithInput(
        tag.tagName,
        value => {
          if (!value) {
            this.$toast("标签名不能为空", "error");
            return;
          }
          if (value === tag.tagName) {
            this.$toast("标签名重复", "error");
            return;
          }
          this.modifyTag({
            tagId: tag.tagId,
            tagName: value
          })
            .then(data => {
              this.$toast("修改成功");
              this.getTList();
            })
            .catch(err => {
              this.$toast(err.msg, "error");
            });
        },
        "请输入新的标签名"
      );
    },
    underTag(tag) {
      this.showDialog(
        "此操作会将该标签删除，并将所有文章移除该标签, 是否继续?",
        () => {
          this.deleteTag(tag.tagId)
            .then(data => {
              this.$toast("已删除");
              this.getTList();
            })
            .catch(err => {
              this.$toast(err.msg, "error");
            });
        }
      );
    },
    toList(type, id) {
      this.$router.push({
        name: "adminArticleList",
        query: {
          type: type,
          id: id
        }
      });
    },
    editCategory(category) {
      this.showDialogWithInput(
        category.categoryName,
        value => {
          if (!value) {
            this.$toast("分类名不能为空", "error");
            return;
          }
          if (value === category.categoryName) {
            this.$toast("分类名重复", "error");
            return;
          }
          this.modifyCategory({
            categoryId: category.categoryId,
            categoryName: value
          })
            .then(data => {
              this.$toast("修改成功");
              this.getCList();
            })
            .catch(err => {
              this.$toast(err.msg, "error");
            });
        },
        "请输入新的分类名"
      );
    },
    underCategory(category) {
      this.showDialog(
        "此操作会删除该分类，并将该分类下的文章移到默认分类, 是否继续?",
        () => {
          this.deleteCategory(category.categoryId)
            .then(data => {
              this.$toast("已删除");
              this.getCList();
            })
            .catch(err => {
              this.$toast(err.msg, "error");
            });
        }
      );
    },
    newCategory() {
      this.showDialogWithInput(
        "新增分类",
        value => {
          if (!value) {
            this.$toast("分类名不能为空", "error");
            return;
          }
          this.addCategory(value)
            .then(data => {
              this.$toast("添加成功");
              this.getCList();
            })
            .catch(err => {
              this.$toast(err.msg, "error");
            });
        },
        "请输入新的分类名"
      );
    },
    newTag() {
      this.showDialogWithInput(
        "新增标签",
        value => {
          if (!value) {
            this.$toast("标签名不能为空", "error");
            return;
          }
          this.addTag(value)
            .then(data => {
              this.$toast("添加成功");
              this.getTList();
            })
            .catch(err => {
              this.$toast(err.msg, "error");
            });
        },
        "请输入新的标签名"
      );
    },
    showDialog(tip, next) {
      this.$confirm(tip, "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning",
        center: true
      })
        .then(() => {
          next();
        })
        .catch(() => {});
    },
    showDialogWithInput(tip, next, placeholder) {
      this.$prompt(tip, "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        inputPlaceholder: placeholder
      })
        .then(({ value }) => {
          next(value);
        })
        .catch(() => {});
    },
    getCList() {
      this.getCategoryList({ all: true })
        .then(data => {
          console.log("category", data);
          this.catetoryList = data;
        })
        .catch(() => {
          this.catetoryList = [];
        });
    },
    getTList() {
      this.getTagList({ all: true })
        .then(data => {
          console.log("tag", data);
          this.tagList = data;
        })
        .catch(() => {
          this.tagList = [];
        });
    }
  }
};
</script>

<style lang="stylus" scoped>
@import '~STYLUS/color.styl';
@import '~STYLUS/mixin.styl';

#category-tag {
  position: relative;
  padding-top: 52px;

  > p {
    position: fixed;
    width: 100%;
    top: 0;
    padding: 18px;
    font-size: 16px;
    font-weight: bold;
    background-color: $color-white;
    box-shadow: 1px 1px 10px 1px rgba(38, 42, 48, 0.1);
    z-index: 1000;
  }

  .category-tag-wrap {
    position: relative;
    animation: show 0.8s;
    display: flex;
    flex-direction: row;
    flex-wrap: wrap;
    margin: 10px;

    .table-wrap {
      position: relative;
      width: calc(50% - 10px);
      margin: 5px;
      transition: all 0.3s;

      @media (max-width: 1009px) {
        width: calc(100% - 10px);
      }

      .action-btn-wrap {
        position: relative;
        background-color: #eeeeee;
        padding: 5px;

        > span {
          display: inline-block;
          padding: 8px 10px;
          font-size: 14px;
          cursor: pointer;
          background-color: $color-main;
          color: $color-white;
          border-radius: 8px;

          &:hover {
            background-color: lighten($color-main, 10%);
          }
        }
      }
    }
  }
}

.title {
  cursor: pointer;

  &:hover {
    color: #29b6f6;
  }
}

@keyframes show {
  from {
    margin-top: 0px;
    opacity: 0;
  }

  to {
    margin-top: 10px;
    opacity: 1;
  }
}
</style>

<style lang="stylus">
.el-message-box__wrapper {
  .el-message-box {
    width: auto !important;
    max-width: calc(100% - 40px);

    .el-message-box__content {
      font-size: 14px;

      @media (max-width: 760px) {
        font-size: 12px;
      }
    }
  }
}
</style>
