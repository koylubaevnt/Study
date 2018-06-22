import { Injectable } from "@angular/core";
import { Http, Response } from "@angular/http";
import { Observable } from "rxjs/Observable";

import { Product } from "./product";

@Injectable()
export class ProductService {

    // адрес сервиса
    private url = 'http://localhost:2403/products';

    constructor(private http: Http) { }

    // Отправка GET запроса на сервер
    public getProducts(): Promise<Product[]> {
        let products: Promise<Product[]> = this.http.get(this.url)
            .toPromise()    
            .then(this.extractProducts) // преобразовывает ответ в массив экземпляров Product.
            .catch(this.handleError);
        return products;
    }

    public getProduct(id: string): Promise<Product> {
        let product: Promise<Product> = this.http.get(this.url + "/" + id)
            .toPromise()    
            .then(this.extractProduct) // преобразовывает ответ в экземпляр Product.
            .catch(this.handleError);
        return product;
    }

    // Отправка POST запроса на сервер, добавление нового продукта в базу.
    public addProduct(product: Product) {
        return this.http.post(this.url, product)
            .toPromise()
            .catch(this.handleError);
    }

    // Отправка PUT запроса и обновление продукта в базе.
    public updateProduct(product: Product) {
        return this.http.put(this.url + "/" + product.id, product)
            .toPromise()
            .catch(this.handleError);
    }

    // Отправка DELETE запроса и удаление продукта из базы.
    public deleteProduct(product: Product) {
        return this.http.delete(this.url + "/" + product.id)
            .toPromise()
            .catch(this.handleError);
    }

    private extractProducts(response: Response): Product[] {
        let res = response.json();
        let products: Product[] = [];
        for(let i = 0; i < res.length; i++) {
            products.push(new Product(res[i].id, res[i].name, res[i].price));
        }
        return products;
    }

    private extractProduct(response: Response): Product {
        let res = response.json();
        let product: Product = new Product(res.id, res.name, res.price);
        return product;
    }

    private handleError(error: any): any {
        let message = '';

        if(error instanceof Response) {
            let errorData = error.json().error || JSON.stringify(error.json());
            message = `${error.status} - ${error.statusText || ''} ${errorData}`;
        } else {
            message = error.message ? error.message : error.toString();
        }

        console.log(message);

        return Observable.throw(message);
    }
}