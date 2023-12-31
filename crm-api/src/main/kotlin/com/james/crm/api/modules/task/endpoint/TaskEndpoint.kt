/*
 * @Author: james.aworo
 * @Date: 11/16/23
 *
 * @Project: agent-crm
 */

package com.james.crm.api.modules.task.endpoint

import com.james.crm.api.core.common.ApiResponse
import com.james.crm.api.core.common.ResourceId
import com.james.crm.api.core.constant.Route
import com.james.crm.api.modules.pipeline.data.dto.PipelineDto
import com.james.crm.api.modules.task.data.dto.*
import com.james.crm.api.modules.task.data.usecase.contract.*
import com.james.crm.api.modules.team.data.usecase.contract.*
import jakarta.validation.Valid
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("${Route.API_VERSION}/tasks")
class TaskEndpoint(
    private val createTaskUsecase: ICreateTaskUsecase,
    private val setTaskParametersUsecase: ISetTaskParametersUsecase,
    private val setTaskStatusUsecase: ISetTaskStatusUsecase,
    private val assignPipelineToTaskUsecase: IAssignPipelineToTaskUsecase,
    private val viewAssociatedPipelinesUsecase: IViewAssociatedPipelinesUsecase,
    private val viewTaskListUsecase: IViewTaskListUsecase,
    private val monitorTaskBudgetUsecase: IMonitorTaskBudgetUsecase,
    private val trackTaskCommissionUsecase: ITrackTaskCommissionUsecase,
    private val manageTaskTimelinesUsecase: IManageTaskTimelinesUsecase,
    private val evaluateTaskPerformanceUsecase: IEvaluateTaskPerformanceUsecase
) {

    @PostMapping
    fun createTask(
        @Valid @RequestBody input: TaskDto
    ): ResponseEntity<ApiResponse<ResourceId>> {
        return createTaskUsecase.execute(input)
    }

    @PutMapping("{taskId}/set-parameters")
    fun setTaskParameters(
        @PathVariable(required = true) taskId: String,
        @RequestBody input: SetTaskParametersInput
    ): ResponseEntity<ApiResponse<Boolean>> {
        return setTaskParametersUsecase.execute(input)
    }

    @PutMapping("{taskId}/set-status")
    fun setTaskStatus(
        @PathVariable(required = true) taskId: String,
        @RequestBody input: SetTaskStatusInput
    ): ResponseEntity<ApiResponse<Boolean>> {
        return setTaskStatusUsecase.execute(input)
    }

    @PutMapping("{taskId}/assign-pipeline/{pipelineId}")
    fun assignPipelineToTask(
        @PathVariable(required = true) taskId: String,
        @PathVariable(required = true) pipelineId: String,
    ): ResponseEntity<ApiResponse<Boolean>> {
        return assignPipelineToTaskUsecase.execute(Pair(taskId, pipelineId))
    }

    @GetMapping("{taskId}/associated-pipelines")
    fun viewAssociatedPipelines(
        @PathVariable(required = true) taskId: String
    ): ResponseEntity<ApiResponse<List<PipelineDto>>> {
        return viewAssociatedPipelinesUsecase.execute(taskId)
    }

    @GetMapping
    fun viewTaskList(): ResponseEntity<ApiResponse<List<TaskDto>>> {
        return viewTaskListUsecase.execute(Unit)
    }

    @GetMapping("{taskId}/monitor-budget")
    fun monitorTaskBudget(
        @PathVariable(required = true) taskId: String
    ): ResponseEntity<ApiResponse<TaskBudgetDto>> {
        return monitorTaskBudgetUsecase.execute(taskId)
    }

    @GetMapping("{taskId}/track-commission")
    fun trackTaskCommission(
        @PathVariable(required = true) taskId: String
    ): ResponseEntity<ApiResponse<TaskCommissionDto>> {
        return trackTaskCommissionUsecase.execute(taskId)
    }

    @PutMapping("{taskId}/manage-timelines")
    fun manageTaskTimelines(
        @PathVariable(required = true) taskId: String,
        @RequestBody input: ManageTaskTimelinesInput
    ): ResponseEntity<ApiResponse<Boolean>> {
        return manageTaskTimelinesUsecase.execute(input)
    }

    @GetMapping("{taskId}/evaluate-performance")
    fun evaluateTaskPerformance(
        @PathVariable(required = true) taskId: String
    ): ResponseEntity<ApiResponse<TaskPerformanceDto>> {
        return evaluateTaskPerformanceUsecase.execute(taskId)
    }
}
