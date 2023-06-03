package com.dojinyou.kotlinjpatest.controller

import com.fasterxml.jackson.annotation.JsonAlias
import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.databind.PropertyNamingStrategies
import com.fasterxml.jackson.databind.annotation.JsonNaming
import java.net.URL
import java.time.Instant

/**
 *  [typeform 웹훅 예제 payload](https://www.typeform.com/developers/webhooks/example-payload/)
 */
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy::class)
data class TypeFormPayload(
    val eventId: String, // Unique ID for the webhook. Automatically assigned by Typeform.
    val eventType: String, // Reason the webhook is being sent.
    val formResponse: FormResponse,
) {
    @JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy::class)
    data class FormResponse(
        val formId: String, // 질문폼 식별자
        val token: String, // 답변 제출 식별자
        val submittedAt: Instant, // UTC
        val landedAt: Instant, // UTC
//        val calculated: Calculated,
//        val variables: List<Variable>,
//        val hidden: Map<String, String>,
//        val definition: Definition,
        val answers: List<Answer>,
//        val ending: Ending,
    ) {
        @JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy::class)
        data class Calculated(
            val score: Int,
        )

        @JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy::class)
        data class Variable(
            val key: String,
            val type: String,
            val number: String? = null,
            val text: String? = null,
        )

        @JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy::class)
        data class Definition(
            val id: String,	// Unique ID for the typeform. Find in your form URL. For example, in the URL https://mysite.typeform.com/to/u6nXL7, the form_id is u6nXL7.
            val title: String,	// Title of the typeform.
            val fields: List<Field>,	// Questions in your typeform. Order of the fields in this questions array matches the order of fields in the answers array (which is included later in the payload).
        ) {

            @JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy::class)
            data class Field(
                val id: String, // Unique ID for the field. You can use the field id to match questions with answers.
                val title: String, // Title of the question associated with the field.
                val type: String, // Question type.
                val ref: String, // A name you can use to reference the field. When you create a form with the Create API, you can specify a readable ref for each field. If you don't specify a ref or you create your form through the Typeform admin panel, our system will generate a non-persistent ref for each field. The system-generated ref will look something like 0e1178a0-67f0-4779-90f1-78e7420f49c9 and will be different in every payload. It must be less than 255 characters and in valid regular expression format ^[a-zA-Z0-9_-]+$.
                val allowMultipleSelections: Boolean, // true if respondents can select more than one answer choice. false if respondents can select only one answer choice or the question is not a multiple_choice or picture_choice question type.
                val allowOtherChoice: Boolean, // true if the question includes an "Other" option so respondents can enter a different answer choice from those listed. false if the question limits answer choices to those listed or the question is not a multiple_choice or picture_choice question type.
                val choices: List<Choice> = emptyList(), // if multiple_choice, picture_choice, dropdown question type, contains a list of choices for this field.
            ) {
                @JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy::class)
                data class Choice(
                    val id: String, // Unique ID for the choice.
                    val label: String, // Label for the choice.
                )
            }
        }

        @JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy::class)
        data class Ending(
            val id: String,
            val ref: String,
        )

        @JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy::class)
        class Answer(
            val type: String,
            val typedField: TypedField,
        ) {
            sealed class TypedField {
                data class TextField(
                    val text: String,
                    override val field: Field,
                ) : TypedField()
                data class ChoiceField(
                    val choice: Choice,
                    override val field: Field,
                ) : TypedField() {
                    data class Choice(
                        val label: String,
                        val other: String? = null,
                    )
                }
                data class EmailField(
                    val email: String,
                    override val field: Field,
                ) : TypedField()
                data class DateField(
                    val date: String,
                    override val field: Field,
                ) : TypedField()
                data class BooleanField(
                    val boolean: Boolean,
                    override val field: Field,
                ) : TypedField()
                data class UrlField(
                    val url: URL,
                    override val field: Field,
                ) : TypedField()
                data class NumberField(
                    val number: Int,
                    override val field: Field,
                ) : TypedField()
                data class FileUrlField(
                    val fileUrl: String,
                    override val field: Field,
                ) : TypedField()
                data class PaymentField(
                    val payment: Payment,
                    override val field: Field,
                ) : TypedField() {
                    data class Payment(
                        val amount: String,
                        val last4: String,
                        val name: String,
                        val success: Boolean,
                    )
                }
                abstract val field: Field
            }
        }

        @JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy::class)
        data class Field(
            val id: String,
            val type: String,
            val ref: String? = null,
        )
    }
}
