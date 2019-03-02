import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import * as moment from 'moment';
import { JhiAlertService, JhiDataUtils } from 'ng-jhipster';
import { IAudit } from 'app/shared/model/audit.model';
import { AuditService } from './audit.service';
import { IOrders } from 'app/shared/model/orders.model';
import { OrdersService } from 'app/entities/orders';

@Component({
    selector: 'jhi-audit-update',
    templateUrl: './audit-update.component.html'
})
export class AuditUpdateComponent implements OnInit {
    audit: IAudit;
    isSaving: boolean;

    orders: IOrders[];
    createdOnDp: any;

    constructor(
        protected dataUtils: JhiDataUtils,
        protected jhiAlertService: JhiAlertService,
        protected auditService: AuditService,
        protected ordersService: OrdersService,
        protected activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ audit }) => {
            this.audit = audit;
        });
        this.ordersService
            .query()
            .pipe(
                filter((mayBeOk: HttpResponse<IOrders[]>) => mayBeOk.ok),
                map((response: HttpResponse<IOrders[]>) => response.body)
            )
            .subscribe((res: IOrders[]) => (this.orders = res), (res: HttpErrorResponse) => this.onError(res.message));
    }

    byteSize(field) {
        return this.dataUtils.byteSize(field);
    }

    openFile(contentType, field) {
        return this.dataUtils.openFile(contentType, field);
    }

    setFileData(event, entity, field, isImage) {
        this.dataUtils.setFileData(event, entity, field, isImage);
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.audit.id !== undefined) {
            this.subscribeToSaveResponse(this.auditService.update(this.audit));
        } else {
            this.subscribeToSaveResponse(this.auditService.create(this.audit));
        }
    }

    protected subscribeToSaveResponse(result: Observable<HttpResponse<IAudit>>) {
        result.subscribe((res: HttpResponse<IAudit>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
    }

    protected onSaveSuccess() {
        this.isSaving = false;
        this.previousState();
    }

    protected onSaveError() {
        this.isSaving = false;
    }

    protected onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }

    trackOrdersById(index: number, item: IOrders) {
        return item.id;
    }
}
