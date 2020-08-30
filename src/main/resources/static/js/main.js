
function getIndex(list, id) {       //функция определяющая индекс элемента в коллекции
    for (var i = 0; i < list.length; i++ ) {
        if (list[i].id === id) {
            return i;
        }
    }

    return -1;
}


var productApi = Vue.resource('/product{/id}'); // Указываем URL по которому будем обращаться к серверу (такой же как у контроллера {необязательная часть})

Vue.component('product-form', {
    props: ['products', 'productAttr'],
    data: function() {
        return {
            product_name: '',
            description: '',
            price: '',
            id: ''
        }
    },
    watch: {
        productAttr: function(newVal, oldVal) {
            this. product_name = newVal. product_name;
            this.description = newVal.description;
            this.price = newVal.price;
            this.id = newVal.id;
        }
    },
    template:
        '<div>' +
        '<input type="product_name" placeholder="Write name" v-model="product_name" />' +
        '<input type="description" placeholder="Write description" v-model="description" />' +
        '<input type="price" placeholder="Write price" v-model="price" />' +
        '<input type="button" value="Save" @click="save" />' +
        '</div>',
    methods: {
        save: function() {
            var product = { product_name: this.product_name, description: this.description, price: this.price };

            if (this.id) {
                productApi.update({id: this.id}, product).then(result =>
                    result.json().then(data => {
                        var index = getIndex(this.products, data.id);
                        this.products.splice(index, 1, data);
                        this. product_name = ''
                        this.description = ''
                        this.price = ''
                        this.id = ''
                    })
                )
            } else {
                productApi.save({}, product).then(result =>
                    result.json().then(data => {
                        this.products.push(data);
                        this. product_name = ''
                        this.description = ''
                        this.price = ''
                    })
                )
            }
        }
    }
});

Vue.component('product-row', {
    props: ['product', 'editMethod', 'products'],
    template: '<div>' +
        '<i>({{ product.id }})</i> {{ product.product_name }} {{ product.description }} {{ product.price }}' +
        '<span style="position: absolute; right: 0">' +
        '<input type="button" value="Edit" @click="edit" />' +
        '<input type="button" value="X" @click="del" />' +
        '</span>' +
        '</div>',
    methods: {
        edit: function() {
            this.editMethod(this.product);
        },
        del: function() {
            productApi.remove({id: this.product.id}).then(result => {
                if (result.ok) {
                    this.products.splice(this.products.indexOf(this.product), 1)
                }
            })
        }
    }
});

Vue.component('products-list', {
    props: ['products'],
    data: function() {
        return {
            product: null
        }
    },
    template:
        '<div style="position: relative; width: 300px;">' +
        '<product-form :products="products" :productAttr="product" />' +
        '<product-row v-for="product in products" :key="product.id" :product="product" ' +
        ':editMethod="editMethod" :products="products" />' +
        '</div>',
    created: function() {
        productApi.get().then(result =>             //указываем что это относитмся к get методу
            result.json().then(data =>
                data.forEach(product => this.product.push(product))
            )
        )
    },
    methods: {
        editMethod: function(product) {
            this.product = product;
        }
    }
});

var app = new Vue({
    el: '#app',
    template: '<products-list :products="products" />',
    data: {
        products: []
    }
});