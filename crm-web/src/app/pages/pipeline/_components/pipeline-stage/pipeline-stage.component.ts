import {Component, Input, OnInit} from '@angular/core';
import {IOption} from '../../../shared/data/shared.interface';
import {UtilService} from '../../../shared/service/util.service';
import {Stage} from '../../_data/pipeline.class';
import {StageAction, StageReviewType} from '../../_data/pipeline.enum';

@Component({
    selector: 'ngx-pipeline-stage',
    templateUrl: './pipeline-stage.component.html',
    styleUrls: ['./pipeline-stage.component.scss'],
})
export class PipelineStageComponent implements OnInit {
    @Input()
    stage?: Stage;

    stageReviewTypes: IOption[] = [];
    stageActions: IOption[] = [];

    constructor(private util: UtilService) {
    }

    ngOnInit(): void {
        this.stageReviewTypes = this.util.mapToOptionList(StageReviewType);
        this.stageActions = this.util.mapToOptionList(StageAction);
    }


}
