import { IShippingRegion } from 'app/shared/model/shipping-region.model';

export interface IShipping {
    id?: number;
    shippingType?: string;
    shippingCost?: number;
    shippingRegions?: IShippingRegion[];
}

export class Shipping implements IShipping {
    constructor(
        public id?: number,
        public shippingType?: string,
        public shippingCost?: number,
        public shippingRegions?: IShippingRegion[]
    ) {}
}
