import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { map } from 'rxjs/operators';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IReview } from 'app/shared/model/review.model';

type EntityResponseType = HttpResponse<IReview>;
type EntityArrayResponseType = HttpResponse<IReview[]>;

@Injectable({ providedIn: 'root' })
export class ReviewService {
    public resourceUrl = SERVER_API_URL + 'api/reviews';

    constructor(protected http: HttpClient) {}

    create(review: IReview): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(review);
        return this.http
            .post<IReview>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    update(review: IReview): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(review);
        return this.http
            .put<IReview>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http
            .get<IReview>(`${this.resourceUrl}/${id}`, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http
            .get<IReview[]>(this.resourceUrl, { params: options, observe: 'response' })
            .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    protected convertDateFromClient(review: IReview): IReview {
        const copy: IReview = Object.assign({}, review, {
            createdOn: review.createdOn != null && review.createdOn.isValid() ? review.createdOn.format(DATE_FORMAT) : null
        });
        return copy;
    }

    protected convertDateFromServer(res: EntityResponseType): EntityResponseType {
        if (res.body) {
            res.body.createdOn = res.body.createdOn != null ? moment(res.body.createdOn) : null;
        }
        return res;
    }

    protected convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
        if (res.body) {
            res.body.forEach((review: IReview) => {
                review.createdOn = review.createdOn != null ? moment(review.createdOn) : null;
            });
        }
        return res;
    }
}
