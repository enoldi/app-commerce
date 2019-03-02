import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { JhiDataUtils } from 'ng-jhipster';

import { IAudit } from 'app/shared/model/audit.model';

@Component({
    selector: 'jhi-audit-detail',
    templateUrl: './audit-detail.component.html'
})
export class AuditDetailComponent implements OnInit {
    audit: IAudit;

    constructor(protected dataUtils: JhiDataUtils, protected activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ audit }) => {
            this.audit = audit;
        });
    }

    byteSize(field) {
        return this.dataUtils.byteSize(field);
    }

    openFile(contentType, field) {
        return this.dataUtils.openFile(contentType, field);
    }
    previousState() {
        window.history.back();
    }
}
