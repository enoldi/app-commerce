import { IAttribute } from 'app/shared/model/attribute.model';

export interface IAttributeValue {
    id?: number;
    name?: string;
    attribute?: IAttribute;
}

export class AttributeValue implements IAttributeValue {
    constructor(public id?: number, public name?: string, public attribute?: IAttribute) {}
}
