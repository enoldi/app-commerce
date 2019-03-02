import { ICategory } from 'app/shared/model/category.model';
import { IAttribute } from 'app/shared/model/attribute.model';
import { IShoppingCard } from 'app/shared/model/shopping-card.model';
import { IOrderItems } from 'app/shared/model/order-items.model';

export interface IProduct {
    id?: number;
    name?: string;
    description?: string;
    price?: number;
    discountedPrice?: string;
    image?: string;
    image2?: string;
    thumbnail?: string;
    display?: number;
    categories?: ICategory[];
    attributes?: IAttribute[];
    shoppingCard?: IShoppingCard;
    orderItems?: IOrderItems;
}

export class Product implements IProduct {
    constructor(
        public id?: number,
        public name?: string,
        public description?: string,
        public price?: number,
        public discountedPrice?: string,
        public image?: string,
        public image2?: string,
        public thumbnail?: string,
        public display?: number,
        public categories?: ICategory[],
        public attributes?: IAttribute[],
        public shoppingCard?: IShoppingCard,
        public orderItems?: IOrderItems
    ) {}
}
