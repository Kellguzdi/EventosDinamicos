<template>
  <b-modal
    id="addBookModal"
    title="Añadir Libro"
    no-close-on-backdrop
    hide-header-close
    no-close-on-esc
    ok-title="Añadir"
    ok-variant="success"
    cancel-title="Cancelar"
    cancel-variant="danger"
    @ok="addBook"
    :ok-disabled="newBook.title.trim() === ''"
    @cancel="closeModal"
  >
    <b-container fluid>
      <form @submit.prevent="addBook">
        <b-form-group label="Título:" label-for="title">
          <b-form-input v-model="newBook.title" id="title" required />
        </b-form-group>
        <b-form-group label="Autor:" label-for="author">
          <b-form-select
            v-model="newBook.autor.id"
            :options="authors"
            value-field="id"
            text-field="name"
            required
          />
        </b-form-group>
        <b-form-group label="Género:" label-for="category">
          <b-form-select
            v-model="newBook.categoria.id"
            :options="categories"
            value-field="id"
            text-field="nameCategory"
            required
          />
        </b-form-group>
        <b-form-group label="Año de publicación:" label-for="issueDate">
          <b-form-input
            v-model="newBook.issueDate"
            id="issueDate"
            type="number"
            required
          />
        </b-form-group>
        <b-form-group label="Imagen:" label-for="image">
          <b-form-input v-model="newBook.image" id="image" required />
        </b-form-group>
      </form>
      <ModalSpinner :isLoading="isLoading" />
    </b-container>
  </b-modal>
</template>

<script>
import Vue from "vue";
import bookService from "../books/Book";
import authorsService from "../authors/Author";
import categoriesService from "../categories/Category";

export default Vue.extend({
  components: {
    ModalSpinner: () => import("../components/Modal.vue"),
  },
  data() {
    return {
      newBook: {
        title: "",
        autor: {
          id: null,
        },
        categoria: {
          id: null,
        },
        issueDate: null,
        image: "",
      },
      authors: [],
      categories: [],
      isLoading: false,
    };
  },
  mounted() {
    this.closeModal();
    this.limpiar();
    this.getAuthors();
    this.getCategories();
  },
  methods: {
    async addBook() {
      this.isLoading = true;
      try {
        const publicationDate = new Date(this.newBook.issueDate, 0, 1);
        this.newBook.issueDate = publicationDate;
        const response = await bookService.addBook(this.newBook);

        if (!response.error) {
          this.isLoading = false;
          this.limpiar();
          this.$emit("bookAdded");
          this.$bvModal.hide("addBookModal");
        }
      } catch (error) {
        console.log(error);
        this.isLoading = false;
      }
    },
    async getAuthors() {
      try {
        const data = await authorsService.getAuthors();
        this.authors = data.data;
      } catch (error) {
        console.log(error);
      }
    },
    async getCategories() {
      try {
        const data = await categoriesService.getCategories();
        this.categories = data.data;
      } catch (error) {
        console.log(error);
      }
    },
    closeModal() {
      this.limpiar();
      this.$bvModal.hide("addBookModal");
    },
    limpiar() {
      this.newBook = {
        title: "",
        autor: {
          id: null,
        },
        categoria: {
          id: null,
        },
        issueDate: null,
        image: "",
      };
    },
  },
});
</script>

<style></style>
