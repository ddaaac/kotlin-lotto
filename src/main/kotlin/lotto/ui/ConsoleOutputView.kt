package lotto.ui

import lotto.domain.LottoRanking
import lotto.ui.dto.LottoStatisticsDto
import lotto.ui.dto.LottoTicketDto
import lotto.ui.dto.LottoTicketsCountDto
import lotto.ui.dto.LottoTicketsDto

object ConsoleOutputView {

    fun printTicketCount(ticketCount: LottoTicketsCountDto) {
        println("수동으로 ${ticketCount.manual}장, 자동으로 ${ticketCount.auto}개 구매했습니다.")
    }

    fun printLottoTickets(tickets: LottoTicketsDto) {
        tickets.tickets.forEach(::printLottoTicket)
    }

    private fun printLottoTicket(ticket: LottoTicketDto) {
        val numbers = ticket.lottoNumbers.joinToString(", ")
        println("[${numbers}]")
    }

    fun printStatistics(statistics: LottoStatisticsDto) {
        println("당첨 통계\n" + "------------------------")
        statistics.statistics.forEach { (ranking, count) ->
            printStatistics(ranking, count)
        }
        println("총 수익률은 %.2f입니다.".format(statistics.revenue))
    }

    private fun printStatistics(ranking: LottoRanking, count: Int?) {
        val bonus = if (ranking.bonus == true) {
            ", 보너스볼 일치"
        } else {
            ""
        }
        println("${ranking.count}개 일치${bonus} (${ranking.reward}) - ${count}개")
    }
}
